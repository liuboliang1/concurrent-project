package com.lbl.concurrent.pool.queue.impl;

import com.lbl.concurrent.pool.policy.DenyPolicy;
import com.lbl.concurrent.pool.queue.RunnableQueue;
import com.lbl.concurrent.pool.threadpool.ThreadPool;

import java.util.LinkedList;

public class LinkedRunnableQueue implements RunnableQueue {

    //存放任务的队列
    private final LinkedList<Runnable> runnableList = new LinkedList<>();

    //任务队列的最大容量
    private final int limit;

    //当队列满时，拒绝策略
    private final DenyPolicy denyPolicy;

    //线程池接口
    private final ThreadPool threadPool;

    public LinkedRunnableQueue(int limit, DenyPolicy denyPolicy, ThreadPool threadPool) {
        this.limit = limit;
        this.denyPolicy = denyPolicy;
        this.threadPool = threadPool;
    }

    @Override
    public void offer(Runnable runnable) {
        synchronized (runnableList) {
            //无法容纳新的任务时执行拒绝策略
            if(runnableList.size() >= limit) {
                denyPolicy.reject(runnable, threadPool);
            }else {
                //将任务加入到队尾，并且唤醒阻塞中的线程
                runnableList.addLast(runnable);
                runnableList.notifyAll();
            }
        }
    }

    @Override
    public Runnable take() throws InterruptedException {

        synchronized (runnableList) {
            //如果队列中没有可执行的任务，则将当前线程挂起
            while(runnableList.isEmpty()) {
                try {
                    runnableList.wait();
                } catch (InterruptedException e) {
                    //被中断时需要将当前异常抛出
                    throw e;
                }
            }
            //从任务队列队头移除一个任务
            return runnableList.removeFirst();
        }

    }

    @Override
    public int size() {
        synchronized (runnableList) {
            return runnableList.size();
        }
    }
}
