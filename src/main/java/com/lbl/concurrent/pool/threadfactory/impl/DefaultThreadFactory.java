package com.lbl.concurrent.pool.threadfactory.impl;

import com.lbl.concurrent.pool.threadfactory.ThreadFactory;

import java.util.concurrent.atomic.AtomicInteger;

public class DefaultThreadFactory implements ThreadFactory {

    private static final AtomicInteger GROUP_COUNTER = new AtomicInteger(1);

    private static final ThreadGroup GROUP = new ThreadGroup("MyThreadPoll-"+GROUP_COUNTER.getAndDecrement());

    private static final AtomicInteger COUNTER = new AtomicInteger(0);

    @Override
    public Thread createThread(Runnable runnable) {
        return new Thread(GROUP, runnable, "thread-pool-" + COUNTER.getAndDecrement());
    }
}
