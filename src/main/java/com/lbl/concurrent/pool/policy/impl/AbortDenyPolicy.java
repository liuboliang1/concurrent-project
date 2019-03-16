package com.lbl.concurrent.pool.policy.impl;

import com.lbl.concurrent.pool.exception.RunnableDenyException;
import com.lbl.concurrent.pool.policy.DenyPolicy;
import com.lbl.concurrent.pool.threadpool.ThreadPool;

/**
 * 该拒绝策略会直接向任务提交者抛出异常
 */
public class AbortDenyPolicy implements DenyPolicy {

    @Override
    public void reject(Runnable runnable, ThreadPool threadPool) {
        throw new RunnableDenyException("The runnable " + runnable + " will be abort");
    }
}