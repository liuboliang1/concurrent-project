package com.lbl.concurrent.activeobject.core;

import com.lbl.concurrent.activeobject.request.DisplayStringResquest;
import com.lbl.concurrent.activeobject.request.MakeStringRequest;
import com.lbl.concurrent.activeobject.result.FutureResult;
import com.lbl.concurrent.activeobject.result.Result;

/**
 * ActiveObject的静态代理
 */
class ActiveObjectProxy implements ActiveObject {

    private final Servant servant;

    private final SchedulerThread schedulerThread;

    public ActiveObjectProxy(SchedulerThread schedulerThread, Servant servant) {
        this.schedulerThread = schedulerThread;
        this.servant = servant;
    }


    @Override
    public Result makeString(int count, char c) {
        FutureResult futureResult = new FutureResult();
        this.schedulerThread.invoke(new MakeStringRequest(this.servant, futureResult, count, c));
        return futureResult;
    }

    @Override
    public void displayString(String text) {
        this.schedulerThread.invoke(new DisplayStringResquest(this.servant, text));
    }
}
