package com.lbl.concurrent.activeobject.request;

import com.lbl.concurrent.activeobject.core.Servant;

public class DisplayStringResquest extends MethodResqest {

    private final String text;

    public DisplayStringResquest(Servant servant, String text) {
        super(servant, null);
        this.text = text;
    }

    @Override
    public void execute() {
        servant.displayString(text);
    }
}
