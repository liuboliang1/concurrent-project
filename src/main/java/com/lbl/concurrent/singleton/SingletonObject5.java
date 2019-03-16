package com.lbl.concurrent.singleton;

/**
 * volatile + Doble check 懒汉加载方式
 */
public class SingletonObject5 {

    private volatile static SingletonObject5 instance = null;

    private SingletonObject5() {}

    public static SingletonObject5 getInstance() {

        if(instance == null) {
            synchronized (SingletonObject5.class) {
                if(instance == null) {
                    instance = new SingletonObject5();
                }
            }
        }
        return SingletonObject5.instance;
    }
}
