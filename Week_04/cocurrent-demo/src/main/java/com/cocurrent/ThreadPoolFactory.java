package com.cocurrent;

import com.sun.scenario.effect.impl.prism.PrImage;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author wangdan
 * @date 2021/2/18
 */
public class ThreadPoolFactory implements ThreadFactory {
    private static final Integer CORE_SIZE = 4;
    private static final Integer MAX_SIZE = 10;

    private static final Integer TIME_OUT = 10000;

    private static final Integer QUEUE_SIZE = 200;

    private final String threadName;

    private ThreadPoolFactory(String name){
        this.threadName = name;
    }


    public static ThreadPoolExecutor createPool(String threadName){
        ThreadPoolFactory threadPoolFactory = new ThreadPoolFactory(threadName);
        return new ThreadPoolExecutor(CORE_SIZE, MAX_SIZE, TIME_OUT, TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<>(QUEUE_SIZE), threadPoolFactory, new ThreadPoolExecutor.DiscardPolicy());
    }

    @Override
    public Thread newThread(Runnable r) {
        return new Thread(r, threadName);
    }
}
