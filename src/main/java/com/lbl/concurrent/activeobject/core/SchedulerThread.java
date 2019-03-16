package com.lbl.concurrent.activeobject.core;

import com.lbl.concurrent.activeobject.queue.ActivationQueue;
import com.lbl.concurrent.activeobject.request.MethodResqest;

/**
 * 定时执行器，执行ActivationQueue队列中的MethodResqest
 */
public class SchedulerThread extends Thread {

    private final ActivationQueue activationQueue;

    public SchedulerThread(ActivationQueue activationQueue) {
        this.activationQueue = activationQueue;
    }

    public void invoke(MethodResqest methodResqest) {
        activationQueue.put(methodResqest);
    }

    @Override
    public void run() {
        while (true) {
            activationQueue.take().execute();
        }
    }
}
