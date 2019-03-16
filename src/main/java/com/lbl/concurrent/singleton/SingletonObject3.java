package com.lbl.concurrent.singleton;

/**
 * synchronize + 懒汉加载方式(存在线程安全问题)
 */
public class SingletonObject3 {

    private static SingletonObject3 instance = null;

    private SingletonObject3() {}

    public static synchronized SingletonObject3 getInstance() {
        if(instance == null) {
            instance = new SingletonObject3();
        }
        return SingletonObject3.instance;
    }

}
