package com.cocurrent;

import java.util.Locale;
import java.util.concurrent.ExecutorService;

/**
 * @author wangdan
 * @date 2021/2/18
 */
public class Demo8 {

    public static void main(String[] args){
        ExecutorService pool = ThreadPoolFactory.createPool("test8");
        int count = 0;
        AddOperator addOperator = new AddOperator();
        while (count < 200){
            pool.submit(new Runnable() {
                @Override
                public void run() {
                    addOperator.add();
                }
            });
            count++;
        }
    }

    static class AddOperator{
        Integer result = 0;
        private final Object lock = new Object();
        public void add(){
            synchronized (lock){
                result++;
                System.out.println(result);
            }
        }
        public Integer getResult(){
            return result;
        }
    }
}
