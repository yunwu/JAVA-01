package com.cocurrent;

import java.util.concurrent.*;
import java.util.function.Supplier;

/**
 * CompletableFuture 实现，分析一下completableFuture
 */
public class Demo5 {

    public static void main(String[] args){

        ExecutorService pool = ThreadPoolFactory.createPool("test5");
        CompletableFuture<String> future = CompletableFuture.supplyAsync(new Supplier<String>() {
            @Override
            public String get() {
                return "hello world!";
            }
        }, pool);

        try{
            System.out.println(future.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}
