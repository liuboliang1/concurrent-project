package com.lbl.concurrent.pool.policy.impl;

import com.lbl.concurrent.pool.policy.DenyPolicy;
import com.lbl.concurrent.pool.threadpool.ThreadPool;

/**
 * 该拒绝策略会直接将任务丢弃
 */
public class DiscardDenyPolicy implements DenyPolicy {

    @Override
    public void reject(Runnable runnable, ThreadPool threadPool) {

    }
}
