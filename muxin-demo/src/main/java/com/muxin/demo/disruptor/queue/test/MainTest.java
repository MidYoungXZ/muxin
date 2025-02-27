package com.muxin.demo.disruptor.queue.test;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * @projectname: muxin
 * @filename: MainTest
 * @author: yangxz
 * @data:2024/7/24 13:46
 * @description:
 */
public class MainTest {


    public static void main(String[] args) {
        BlockingDeque<Long> deque = new LinkedBlockingDeque<>(3276800);
        long s = System.currentTimeMillis();
        long count=0L;
        while (true){
            try {
                deque.addLast(count++);
            }catch (Exception e){
                e.printStackTrace();
                break;
            }
        }
        long s1 = System.currentTimeMillis();
        while (true){
            Long l = deque.pollLast();
            System.out.println(l);
            if (null == l){
                break;
            }
        }

        long l2 = System.currentTimeMillis();

        System.err.println((s1-s)+" "+(l2-s));

    }



}
