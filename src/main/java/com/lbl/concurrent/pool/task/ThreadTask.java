package com.lbl.concurrent.pool.task;


/**
 * ThreadTask是InternalTask和Thread的一个组合
 */
public class ThreadTask {

    private Thread thread;
    private InternalTask internalTask;

    public ThreadTask(Thread thread, InternalTask internalTask) {
        this.thread = thread;
        this.internalTask = internalTask;
    }

    public Thread getThread() {
        return thread;
    }

    public InternalTask getInternalTask() {
        return internalTask;
    }
}
