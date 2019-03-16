package com.lbl.concurrent.pool.threadfactory;

/**
 * 创建线程工厂
 */
@FunctionalInterface
public interface ThreadFactory {
    /**
     * 用于创建线程
     * @param runnable
     * @return
     */
    Thread createThread(Runnable runnable);
}
