package com.cocurrent;

/**
 * 1.运用共享变量拿到方法返回值
 */
public class Demo1 {

    private static String result;

    public static void main(String[] args){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                result = "hello world";
            }
        });
        thread.start();
        try{
            Thread.sleep(1000);
        }catch (InterruptedException ex){
            ex.printStackTrace();
        }

        System.out.println(result);
    }
}
