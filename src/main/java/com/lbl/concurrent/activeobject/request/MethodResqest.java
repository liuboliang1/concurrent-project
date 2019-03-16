package com.lbl.concurrent.activeobject.request;

import com.lbl.concurrent.activeobject.core.Servant;
import com.lbl.concurrent.activeobject.result.FutureResult;

public abstract class MethodResqest {

    protected final Servant servant;

    protected final FutureResult futureResult;

    protected MethodResqest(Servant servant, FutureResult futureResult) {
        this.servant = servant;
        this.futureResult = futureResult;
    }

    public abstract void execute();

}
