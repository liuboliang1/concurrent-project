package com.lbl.concurrent.singleton;

/**
 * 枚举方式
 */
public class SingletonObject7 {

    private SingletonObject7() {}

    private enum EnumHolder {

        INSTANCE;

        private SingletonObject7 instance = null;

        EnumHolder() {
            this.instance = new SingletonObject7();
            System.out.println("EnumHolder初始化");
        }

        private SingletonObject7 getInstance() {
            return instance;
        }

    }

    public static SingletonObject7 getInstance() {
        return EnumHolder.INSTANCE.getInstance();
    }

    public static void main(String[] args) {
        SingletonObject7.getInstance();
    }
}
