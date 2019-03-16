package com.lbl.concurrent.singleton;

/**
 * 饿汉加载方式(存在线程安全问题)
 */
public class SingletonObject1 {

    //在定义实例对象的时候直接初始化
    private static SingletonObject1 instance = new SingletonObject1();

    private SingletonObject1() {}

    public static SingletonObject1 getInstance() {
        return SingletonObject1.instance;
    }


}
