package com.lbl.concurrent.pool.queue;

/**
 * 任务队列，主要用于缓存提交到线程池中的任务
 * 主要用于存放提交的Runnable，该Runnable是一个BlockedQueue，并且有limit限制
 */
public interface RunnableQueue {

    /**
     * 当前新的任务进来时首先会offer到队列中
     * @param runnable
     */
    void offer(Runnable runnable);

    /**
     * 工作线程通过take方法获取Runnable
     * @return
     */
    Runnable take() throws InterruptedException;

    /**
     * 获取任务队列中的任务数量
     * @return
     */
    int size();


}
