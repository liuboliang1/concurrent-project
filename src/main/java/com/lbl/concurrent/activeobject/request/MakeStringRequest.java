package com.lbl.concurrent.activeobject.request;

import com.lbl.concurrent.activeobject.core.Servant;
import com.lbl.concurrent.activeobject.result.FutureResult;
import com.lbl.concurrent.activeobject.result.Result;

public class MakeStringRequest extends MethodResqest {

    private final int count;
    private final char c;

    public MakeStringRequest(Servant servant, FutureResult futureResult, int count, char c) {
        super(servant, futureResult);
        this.count = count;
        this.c = c;
    }

    @Override
    public void execute() {
        Result result = servant.makeString(count, c);
        this.futureResult.setResult(result);
    }
}
