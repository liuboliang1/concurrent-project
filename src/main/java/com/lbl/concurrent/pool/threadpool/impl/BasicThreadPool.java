package com.lbl.concurrent.pool.threadpool.impl;

import com.lbl.concurrent.pool.policy.DenyPolicy;
import com.lbl.concurrent.pool.policy.impl.AbortDenyPolicy;
import com.lbl.concurrent.pool.queue.impl.LinkedRunnableQueue;
import com.lbl.concurrent.pool.queue.RunnableQueue;
import com.lbl.concurrent.pool.task.InternalTask;
import com.lbl.concurrent.pool.task.ThreadTask;
import com.lbl.concurrent.pool.threadfactory.impl.DefaultThreadFactory;
import com.lbl.concurrent.pool.threadfactory.ThreadFactory;
import com.lbl.concurrent.pool.threadpool.ThreadPool;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.TimeUnit;

public class BasicThreadPool extends Thread implements ThreadPool {

    private final int initSize;

    private final int maxSize;

    private final int coreSize;

    private int activeCount;

    private final ThreadFactory threadFactory;

    private final RunnableQueue runnableQueue;

    private volatile boolean isShutdown = false;

    private final Queue<ThreadTask> threadTaskQueue = new ArrayDeque<>();

    private final static DenyPolicy DEFAULT_DENY_POLICY = new AbortDenyPolicy();

    private final static ThreadFactory DEFAULT_THREAD_FACTORY = new DefaultThreadFactory();

    private final long keepAliveTime;
    private final TimeUnit timeUnit;

    public BasicThreadPool(int initSize, int maxSize, int coreSize, int queueSize) {
        this(initSize, maxSize, coreSize, DEFAULT_THREAD_FACTORY, queueSize, DEFAULT_DENY_POLICY,
                10, TimeUnit.SECONDS);
    }

    public BasicThreadPool(int initSize, int maxSize, int coreSize,
                           ThreadFactory threadFactory, int queueSize, DenyPolicy denyPolicy,
                           long keepAliveTime, TimeUnit timeUnit) {
        this.initSize = initSize;
        this.maxSize = maxSize;
        this.coreSize = coreSize;
        this.threadFactory = threadFactory;
        this.runnableQueue = new LinkedRunnableQueue(queueSize, denyPolicy, this);
        this.keepAliveTime = keepAliveTime;
        this.timeUnit = timeUnit;

        this.init();
    }

    private void init() {
        start();
        for (int i=0; i<initSize; i++) {
            newThread();
        }
    }

    private void newThread() {
        InternalTask internalTask = new InternalTask(runnableQueue);
        Thread thread = this.threadFactory.createThread(internalTask);
        ThreadTask threadTask = new ThreadTask(thread, internalTask);

        this.threadTaskQueue.offer(threadTask);
        this.activeCount++;
        thread.start();
    }

    private void removeThread() {
        ThreadTask threadTask = this.threadTaskQueue.remove();
        threadTask.getInternalTask().stop();
        this.activeCount--;
    }

    @Override
    public void run() {
        while (!isShutdown && !isInterrupted()) {
            try {
                timeUnit.sleep(keepAliveTime);
            } catch (InterruptedException e) {
                isShutdown = true;
                break;
            }
            synchronized (this) {
                if (isShutdown)
                    break;
                //当前队列中有任务尚未处理，并且activeCount<coreSize则继续扩容
                if (runnableQueue.size() > 0 && activeCount < coreSize) {
                    for (int i=initSize;i<coreSize;i++) {
                        newThread();
                    }
                    //continue的目的在于不想让线程的扩容直接到达maxSize
                    continue;
                }

                //当前队列中有任务尚未处理，并且activeCount<maxSize则继续扩容
                if (runnableQueue.size() > 0 && activeCount < maxSize) {
                    for (int i=coreSize;i<maxSize;i++) {
                        newThread();
                    }
                }
                //如果队列中没有任务，则需要回收，回收至coreSize即可
                if (runnableQueue.size() == 0 && activeCount > coreSize) {
                    for (int i=coreSize;i<activeCount;i++) {
                        removeThread();
                    }
                }
            }
        }
    }

    @Override
    public void execute(Runnable runnable) {
        if (this.isShutdown) {
            throw new IllegalStateException("The thread pool is destory");
        }
        //提交任务只是简单地王任务队列中插入Runnable
        this.runnableQueue.offer(runnable);
    }

    @Override
    public void shutdown() {
        synchronized (this) {
            if (isShutdown) {
                return;
            }
            isShutdown = true;
            threadTaskQueue.forEach(threadTask -> {
                threadTask.getInternalTask().stop();
                threadTask.getThread().interrupt();
            });
            this.interrupt();
        }

    }

    @Override
    public boolean isShutdown() {
        return this.isShutdown;
    }

    @Override
    public int getInitSize() {
        if (this.isShutdown) {
            throw new IllegalStateException("The thread pool is destory");
        }
        return this.initSize;
    }

    @Override
    public int getMaxSize() {
        if (this.isShutdown) {
            throw new IllegalStateException("The thread pool is destory");
        }
        return this.maxSize;
    }

    @Override
    public int getCoreSize() {
        if (this.isShutdown) {
            throw new IllegalStateException("The thread pool is destory");
        }
        return this.coreSize;
    }

    @Override
    public int getQueueSize() {
        if (this.isShutdown) {
            throw new IllegalStateException("The thread pool is destory");
        }
        return this.runnableQueue.size();
    }

    @Override
    public int getActiveCount() {
        if (this.isShutdown) {
            throw new IllegalStateException("The thread pool is destory");
        }
        return this.activeCount;
    }
}
