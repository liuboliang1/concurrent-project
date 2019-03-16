package com.lbl.concurrent.activeobject.core;

import com.lbl.concurrent.activeobject.queue.ActivationQueue;

/**
 * 创建ActiveObject的工厂
 */
public class ActiveObjectFactory {

    public static ActiveObject createActiveObject() {
        Servant servant = new Servant();

        ActivationQueue activationQueue = new ActivationQueue();
        SchedulerThread schedulerThread = new SchedulerThread(activationQueue);

        ActiveObjectProxy proxy = new ActiveObjectProxy(schedulerThread, servant);

        schedulerThread.start();

        return proxy;
    }

}
