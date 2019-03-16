package com.lbl.concurrent.activeobject.core;

import com.lbl.concurrent.activeobject.result.RealResult;
import com.lbl.concurrent.activeobject.result.Result;

/**
 * ActiveObject的真正实现逻辑
 */
class Servant implements ActiveObject {

    @Override
    public Result makeString(int count, char c) {
        char[] buff = new char[count];
        for (int i = 0;i < count; i++) {
            buff[i] = c;
        }
        return new RealResult(String.valueOf(buff));
    }

    @Override
    public void displayString(String text) {
        try {
            System.out.println("Display:" + text);
            Thread.sleep(10);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
