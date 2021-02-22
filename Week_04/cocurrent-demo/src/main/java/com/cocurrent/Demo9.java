package com.cocurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * fork join 的task  + volatile实现
 */
public class Demo9 {

    private static volatile int result;

    public static void main(String[] args){

        //方式1， forktask 提交，volatile刷新数据
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        //int i=0;
        //while (i < 1000){
        //    ForkJoinTask task = new JoinTask();
        //    forkJoinPool.submit(task);
        //    i++;
        //}
        //
        //try {
        //    Thread.sleep(1000);
        //} catch (InterruptedException e) {
        //    e.printStackTrace();
        //}
        //System.out.println(result);
        //方式2
        Integer[] array = new Integer[1000];
        for (int j = 0; j< 1000; j++){
            array[j] = j;
        }
        Future<Integer> result = forkJoinPool.submit(new JoinTask2(array));

        try {
            System.out.println(result.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    static class JoinTask extends RecursiveAction{

        @Override
        protected void compute() {
            System.out.println("计算结果：" + result++);
        }
    }

    static class JoinTask2 extends RecursiveTask<Integer>{

        private static final int threshold = 2;
        private Integer[] array;

        public JoinTask2(Integer[] array){
            this.array = array;
        }

        @Override
        protected Integer compute() {
            int sum = 0;
            if (array.length > threshold){
                List<Integer[]> datas = getHalfArray(array);
                JoinTask2 leftTask = new JoinTask2(datas.get(0));
                JoinTask2 rightTask = new JoinTask2(datas.get(1));
                leftTask.fork();
                rightTask.fork();
                int leftResult = leftTask.join();
                int rightResult = rightTask.join();
                sum = leftResult + rightResult;
            }else if (array.length == threshold){
                return array[0] + array[1];
            }else {
                return array[0];
            }
            return sum;
        }

        private List<Integer[]> getHalfArray(Integer[] allData){
            Integer halfSize = allData.length/2;
            Integer[] left = new Integer[halfSize];
            Integer[]right = new Integer[allData.length - halfSize];
            for (int i = 0; i < halfSize; i++){
                Integer data = allData[i];
                left[i] = data;
            }
            for (int i = 0; i < right.length; i++){
                right[i] = allData[i + halfSize];
            }
            List<Integer[]> list = new ArrayList<>();
            list.add(left);
            list.add(right);
            return list;
        }
    }
}
