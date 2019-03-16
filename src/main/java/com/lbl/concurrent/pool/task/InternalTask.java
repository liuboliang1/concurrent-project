package com.lbl.concurrent.pool.task;

import com.lbl.concurrent.pool.queue.RunnableQueue;

public class InternalTask implements Runnable {

    private final RunnableQueue runnableQueue;

    private volatile boolean running = true;

    public InternalTask(RunnableQueue runnableQueue) {
        this.runnableQueue = runnableQueue;
    }

    @Override
    public void run() {
        while (running && !Thread.currentThread().isInterrupted()) {
            try {
                Runnable task = runnableQueue.take();
                task.run();
            } catch (InterruptedException e) {
                running = false;
                break;
            }
        }
    }

    /**
     * 停止当前的任务，主要用在线程池的shutdown方法中
     */
    public void stop() {
        running = false;
    }
}
