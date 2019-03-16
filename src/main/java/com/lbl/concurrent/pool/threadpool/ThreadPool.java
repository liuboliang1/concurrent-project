package com.lbl.concurrent.pool.threadpool;

/**
 * 定义了一个线程池应该具备的基本操作和方法
 */
public interface ThreadPool {

    /**
     * 该方法接受提交Runable任务
     * @param runnable
     */
    void execute(Runnable runnable);

    /**
     * 关闭并且销毁线程池
     */
    void shutdown();

    /**
     * 判断线程池是否已经被销毁
     * @return
     */
    boolean isShutdown();

    /**
     * 返回线程池的初始线程数量
     * @return
     */
    int getInitSize();

    /**
     * 返回线程池的最大线程数量
     * @return
     */
    int getMaxSize();

    /**
     * 返回线程池的核心线程数量
     * @return
     */
    int getCoreSize();

    /**
     * 返回线程池任务数量
     * @return
     */
    int getQueueSize();

    /**
     * 返回线程池中当前活跃的线程数量
     * @return
     */
    int getActiveCount();

}
