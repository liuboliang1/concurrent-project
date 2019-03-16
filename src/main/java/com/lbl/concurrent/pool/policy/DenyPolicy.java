package com.lbl.concurrent.pool.policy;

import com.lbl.concurrent.pool.threadpool.ThreadPool;

/**
 * 拒绝策略接口
 */
@FunctionalInterface
public interface DenyPolicy {

    void reject(Runnable runnable, ThreadPool threadPool);

}
