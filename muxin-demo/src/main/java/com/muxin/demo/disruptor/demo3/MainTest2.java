package com.muxin.demo.disruptor.demo3;

/**
 * @projectname: muxin
 * @filename: MainTest2
 * @author: yangxz
 * @data:2024/3/21 17:30
 * @description:
 */
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.SequenceBarrier;
import com.lmax.disruptor.WorkerPool;
import com.lmax.disruptor.YieldingWaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import com.muxin.demo.disruptor.demo2.Order;
import com.muxin.demo.disruptor.demo2.OrderExceptionHandler;
import com.muxin.demo.disruptor.demo2.OrderProducer;

import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;

public class MainTest2 {

    /**
     * 多生产者多消费者(独立关系-重复消费)
     * @param args
     */
    public static void main(String[] args) {
        // 环形数组大小
        int ringBufferSize = 1024 * 1024;

        // 创建多个消费者
        OrderEventHandler[] eventHandlers = new OrderEventHandler[4];
        for (int i = 0; i < eventHandlers.length; i++) {
            eventHandlers[i] = new OrderEventHandler(i);
        }

        Disruptor<Order> disruptor = new Disruptor<>(() -> new Order(), ringBufferSize,
                Executors.defaultThreadFactory(), ProducerType.MULTI, new YieldingWaitStrategy());
        disruptor.setDefaultExceptionHandler(new OrderExceptionHandler());
        // 异常处理
        disruptor.handleEventsWith(eventHandlers);
        //  启动队列（启动消费者）
        disruptor.start();

        RingBuffer<Order> ringBuffer = disruptor.getRingBuffer();

        // 模拟100个生产者，每个生产者生产100条消息
        for (int i = 1; i <= 5; i++) {
            OrderProducer orderProducer = new OrderProducer(ringBuffer);
            for (int j = 1; j <= 1; j++) {
                orderProducer.setData(UUID.randomUUID().toString());
                orderProducer.setData(UUID.randomUUID().toString());
                orderProducer.setData(UUID.randomUUID().toString());
                orderProducer.setData(UUID.randomUUID().toString());
                orderProducer.setData(UUID.randomUUID().toString());
            }
        }
        System.exit(0);
    }
}
