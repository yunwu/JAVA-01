package com.cocurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * List<Callable<String>> 实现
 */
public class Demo4 {

    public static void main(String[] args){

        List<Callable<String>> task = new ArrayList<>();
        task.add(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "hello world!";
            }
        });
        task.add(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "hello King";
            }
        });
        task.add(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "hello lili";
            }
        });

        ExecutorService pool = Executors.newFixedThreadPool(1);
        try {
            List<Future<String>> results = pool.invokeAll(task);
            for (Future<String> result : results) {
               System.out.println(result.get());
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

    }
}
