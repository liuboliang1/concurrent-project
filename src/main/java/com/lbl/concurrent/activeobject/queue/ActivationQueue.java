package com.lbl.concurrent.activeobject.queue;

import com.lbl.concurrent.activeobject.request.MethodResqest;

import java.util.LinkedList;

/**
 * 队列，用于存放MethodResqest
 */
public class ActivationQueue {

    private final LinkedList<MethodResqest> methodQueue;

    private final static Integer MAX_METHOD_REQUEST_QUEUE_SIZE = 1000;

    public ActivationQueue() {
        methodQueue = new LinkedList<>();
    }

    public synchronized void put(MethodResqest methodResqest) {

        while(methodQueue.size() >= MAX_METHOD_REQUEST_QUEUE_SIZE) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        methodQueue.addLast(methodResqest);
        this.notifyAll();
    }

    public synchronized MethodResqest take() {
        while (methodQueue.isEmpty()) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        MethodResqest methodResqest = methodQueue.removeFirst();
        this.notifyAll();
        return methodResqest;
    }
}
