package com.muxin.demo.disruptor.demo4;

/**
 * @projectname: muxin
 * @filename: MainTest
 * @author: yangxz
 * @data:2024/3/25 16:55
 * @description:
 */
import com.lmax.disruptor.*;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DisruptorExample {

    // 定义事件类
    static class Event {

        private String data;

        public String getData() {
            return data;
        }

        public void setData(String data) {
            this.data = data;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        // 创建一个线程池
        ExecutorService executor = Executors.newFixedThreadPool(4);

        // 初始化环形缓冲区大小、指定生产者类型为单生产者
        int ringBufferSize = 1024;
        ProducerType producerType = ProducerType.MULTI;

        // 创建 Disruptor 实例
        Disruptor<Event> disruptor = new Disruptor<>(Event::new, ringBufferSize, executor, producerType,
                new YieldingWaitStrategy());

        // 定义事件处理器（消费者）
        EventHandler<Event> consumer1 = new EventHandler<Event>() {
            @Override
            public void onEvent(Event event, long sequence, boolean endOfBatch) throws Exception {
                // 消费者1处理事件的逻辑...
                System.out.println("Consumer 1 processing: " + event);
            }
        };

        EventHandler<Event> consumer2 = new EventHandler<Event>() {
            @Override
            public void onEvent(Event event, long sequence, boolean endOfBatch) throws Exception {
                // 消费者2处理事件的逻辑...
                System.out.println("Consumer 2 processing: " + event);
            }
        };

        // 将消费者添加到 Disruptor 中
        disruptor.handleEventsWith(consumer1, consumer2);

        // 创建并启动消费者处理器
        disruptor.start();

        // 创建生产者，发布事件
        RingBuffer<Event> ringBuffer = disruptor.getRingBuffer();
        EventTranslatorOneArg<Event, String> translator = (event, sequence, data) -> {
            // 设置事件数据
            event.setData(data);
        };

        for (int i = 0; i < 1000; i++) {
            long sequence = ringBuffer.next(); // 获取下一个可用的序列号
            translator.translateTo(ringBuffer.get(sequence), sequence, "Event " + i); // 设置事件内容
            ringBuffer.publish(sequence); // 发布事件，使消费者可见
        }

        // 等待所有事件处理完毕
        disruptor.shutdown(); // 关闭Disruptor会等待所有的事件都被处理

        // 关闭线程池
        executor.shutdown();
    }
}

