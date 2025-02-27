package com.muxin.demo.disruptor.demo1;

/**
 * @projectname: muxin
 * @filename: MainTest
 * @author: yangxz
 * @data:2024/3/21 17:18
 * @description:
 */

import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.YieldingWaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class MainTest {

    /**
     * 一个生产者一个消费者
     * @param args
     */
    public static void main(String[] args) {
        // 创建一个线程工厂提供线程来触发Consumer的事件处理
        ThreadFactory threadFactory = Executors.defaultThreadFactory();

        // 环形数组大小
        int ringBufferSize = 16;

        // 第一个参数是事件工厂。用于生产事件（数据）
        Disruptor<StringEvent> disruptor = new Disruptor<>(() -> new StringEvent(), ringBufferSize,
                threadFactory, ProducerType.SINGLE, new YieldingWaitStrategy());

        // 连接消费者方法
        disruptor.handleEventsWith(new StringEventHandler());


        // 启动消费者
        disruptor.start();

        RingBuffer ringBuffer = disruptor.getRingBuffer();
        // 创建生产者
        StringEventProducer eventProducer = new StringEventProducer(ringBuffer);
        for (int i = 1; i <= 1000; i++) {
            // 生产数据
            eventProducer.setData("数据" + i);
        }
        disruptor.shutdown();
        System.exit(0);
    }
}
