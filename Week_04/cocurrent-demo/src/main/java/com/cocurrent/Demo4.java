package com.cocurrent;

import java.util.concurrent.*;

public class Demo4 {

    public static void main(String[] args){

        ExecutorService pool = new ThreadPoolExecutor(1, 1, 600, TimeUnit.SECONDS, new ArrayBlockingQueue<>(10));

        Future<String> future = pool.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "hello world";
            }
        });
        try{
            System.out.println(future.get(1000, TimeUnit.MILLISECONDS));
        }catch (InterruptedException | ExecutionException | TimeoutException ex){
            ex.printStackTrace();
        }
    }
}
