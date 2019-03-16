package com.lbl.concurrent.singleton;

/**
 * 懒汉加载方式(存在线程安全问题)
 */
public class SingletonObject2 {

    private static SingletonObject2 instance = null;

    private SingletonObject2() {}

    public static SingletonObject2 getInstance() {
        if(instance == null) {
            instance = new SingletonObject2();
        }
        return SingletonObject2.instance;
    }
}
