package com.netty.demo.common;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author wangdan
 * @date 2021/1/31
 */
public class NamedThreadFactory implements ThreadFactory {

    private final ThreadGroup group;
    private final AtomicInteger threadNumber = new AtomicInteger(1);
    private final String namePrefix;
    private final boolean daemon;

    public NamedThreadFactory(String namePrefix, boolean daemon){
        this.daemon = daemon;
        SecurityManager securityManager = System.getSecurityManager();
        group = (securityManager != null) ?securityManager.getThreadGroup():Thread.currentThread().getThreadGroup();
        this.namePrefix = namePrefix;
    }

    public NamedThreadFactory(String namePrefix){
        this(namePrefix, false);
    }


    @Override
    public Thread newThread(Runnable r) {
        Thread thread = new Thread(group, r, namePrefix + "-thread-" + threadNumber.getAndIncrement(), 0);
        thread.setDaemon(daemon);
        return thread;
    }
}
