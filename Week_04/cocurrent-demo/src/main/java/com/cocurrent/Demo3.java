package com.cocurrent;


import java.util.concurrent.*;

/**
 * 利用Future Thread实现
 */
public class Demo3 {

    public static void main(String[] args){

        ExecutorService pool = ThreadPoolFactory.createPool("test3");
        Future<String> future = pool.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "hello world";
            }
        });
        try{
            System.out.println(future.get());
            System.out.println(future.get(1000, TimeUnit.MILLISECONDS));
        }catch (InterruptedException | ExecutionException | TimeoutException ex){
           ex.printStackTrace();
        }
    }
}
