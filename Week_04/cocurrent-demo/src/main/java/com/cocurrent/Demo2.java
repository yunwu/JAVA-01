package com.cocurrent;

import java.util.concurrent.*;

/**
 * 运用Future 拿到结果
 */
public class Demo2 {

    public static void main(String[] args){
        FutureTask<String> futureTask = new FutureTask<String>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "hello world";
            }
        });
        try{
            Thread thread = new Thread(futureTask);
            thread.start();
            System.out.println(futureTask.get());
            System.out.println(futureTask.get(1000, TimeUnit.MILLISECONDS));
        }catch (InterruptedException | ExecutionException | TimeoutException ex){
            ex.printStackTrace();
        }
    }
}
