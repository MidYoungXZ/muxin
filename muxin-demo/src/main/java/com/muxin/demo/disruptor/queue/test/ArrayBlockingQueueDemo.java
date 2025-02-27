package com.muxin.demo.disruptor.queue.test;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @projectname: muxin
 * @filename: ArrayBlockingQueueDemo
 * @author: yangxz
 * @data:2024/7/24 14:20
 * @description:
 */
public class ArrayBlockingQueueDemo {

    public static void main(String[] args) {
        ArrayBlockingQueueDemo demo = new ArrayBlockingQueueDemo();
        demo.testCostTime();
    }

    public void testCostTime(){
        // 1、手工创建线程池
        ThreadPoolExecutor pool =  (ThreadPoolExecutor)Executors.newFixedThreadPool(20);
        // 2、声明队列的大小
        final ArrayBlockingQueue<DataCase> queue = new ArrayBlockingQueue<>(10000000);
        final long startTime = System.currentTimeMillis();
        // 3、提交生产者队列
        for (int i = 0; i < 10; i++) {
            pool.submit(new ProvideRunable(queue));
        }
        // 4、提交消费者队列
        for (int i = 0; i < 10; i++) {
            pool.submit(new CoustmRunable(queue,startTime));
        }
        // 4、关闭队列
        pool.shutdown();
    }

    class ProvideRunable implements Runnable{
        private ArrayBlockingQueue<DataCase> queue;
        // 3.1、构造函数，将队列引入进来
        ProvideRunable(ArrayBlockingQueue<DataCase> queue){
            this.queue=queue;
        }

        @Override
        public void run() {
            long i = 0;
            // 3.2、循环
            while(i < Constants.EVENT_NUM_FM) {
                try {
                    // 3.3、放入元素
                    queue.put(new DataCase(i, "c"+i));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                i++;
            }
        }
    }

    class CoustmRunable implements Runnable{
        private ArrayBlockingQueue<DataCase> queue;
        private long startTime;

        // 4.1、构造函数，引入队列和开始时间
        CoustmRunable(ArrayBlockingQueue<DataCase> queue,long startTime){
            this.queue=queue;
            this.startTime=startTime;
        }

        @Override
        public void run() {
            long k = 0;
            // 4.2、遍历循环
            while (k < Constants.EVENT_NUM_FM) {
                try {
                    // 4.3、取出元素
                    queue.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                k++;
            }
            long endTime = System.currentTimeMillis();
            System.out.println("ArrayBlockingQueue costTime = " + (endTime - startTime) + "ms");
        }
    }
}