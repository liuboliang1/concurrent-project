package com.lbl.concurrent.pool;

import com.lbl.concurrent.pool.threadpool.impl.BasicThreadPool;
import com.lbl.concurrent.pool.threadpool.ThreadPool;

import java.util.concurrent.TimeUnit;

public class ThreadPoolTest {

    public static void main(String[] args) throws InterruptedException {
        final ThreadPool threadPool = new BasicThreadPool(2,6,4,20);
        for (int i = 0; i< 20; i++) {
            threadPool.execute(()->{
                try {
                    TimeUnit.SECONDS.sleep(2);
                    System.out.println(Thread.currentThread().getName() + " is running and done.");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

        while (true) {
            System.out.println("getActiveCount :"+ threadPool.getActiveCount());
            System.out.println("getQueueSize :"+ threadPool.getQueueSize());
            System.out.println("getCoreSize :"+ threadPool.getCoreSize());
            System.out.println("getMaxSize "+ threadPool.getMaxSize());
            System.out.println("=====================================");
            TimeUnit.SECONDS.sleep(5);
        }
    }
}
