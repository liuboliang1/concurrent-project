package com.lbl.concurrent.singleton;

/**
 * Double Check 懒汉加载方式(存在线程安全问题)
 */
public class SingletonObject4 {
    private static SingletonObject4 instance = null;

    private SingletonObject4() {}

    public static SingletonObject4 getInstance() {

        if(instance == null) {
            synchronized (SingletonObject4.class) {
                if(instance == null) {
                    instance = new SingletonObject4();
                }
            }
        }
        return SingletonObject4.instance;
    }
}
