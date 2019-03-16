package com.lbl.concurrent.pool.policy.impl;

import com.lbl.concurrent.pool.policy.DenyPolicy;
import com.lbl.concurrent.pool.threadpool.ThreadPool;

/**
 * 该拒绝策略会是任务在提交者所在的线程中执行任务
 */
public class RunnerDenyPolicy implements DenyPolicy {

    @Override
    public void reject(Runnable runnable, ThreadPool threadPool) {
        if(!threadPool.isShutdown()) {
            runnable.run();
        }
    }
}