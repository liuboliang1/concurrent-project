package com.lbl.concurrent.singleton;

/**
 * Holder方式
 */
public class SingletonObject6 {

    private SingletonObject6() {}

    private static class Holder {
        private static SingletonObject6 instance = new SingletonObject6();
    }

    public static SingletonObject6 getInstance() {
        return Holder.instance;
    }

}
