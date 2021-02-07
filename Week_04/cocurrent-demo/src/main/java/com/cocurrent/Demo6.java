package com.cocurrent;

import java.util.concurrent.*;

public class Demo6 {

    public static void main(String[] args){
        ScheduledThreadPoolExecutor pool = new ScheduledThreadPoolExecutor(1);
        Future<String> future = pool.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "hello world";
            }
        });

        try {
            System.out.println(future.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}
