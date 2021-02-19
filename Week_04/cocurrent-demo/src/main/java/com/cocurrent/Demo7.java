package com.cocurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author wangdan
 * @date 2021/2/18
 */
public class Demo7 {

    public static void main(String[] args){
        ExecutorService pool = ThreadPoolFactory.createPool("test7");
        int count = 0;
        AddOperator addOperator = new AddOperator();
        Lock lock = new ReentrantLock();
        while (count < 200){
            pool.submit(new Runnable() {
                @Override
                public void run() {
                    lock.lock();
                    addOperator.add();
                    System.out.println(addOperator.getResult());
                    lock.unlock();

                }
            });
            count++;
        }
    }

    static class AddOperator{
        Integer result = 0;

        public void add(){
            result++;
        }

        public Integer getResult(){
            return result;
        }
    }
}
