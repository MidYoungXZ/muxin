package com.muxin.demo.disruptor.queue.test;

/**
 * @projectname: muxin
 * @filename: DisruptorExample
 * @author: yangxz
 * @data:2024/7/24 18:03
 * @description:
 */
import com.lmax.disruptor.*;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

// 定义事件
class Event {
    private long value;

    public long getValue() {
        return value;
    }

    public void setValue(long value) {
        this.value = value;
    }
}

// 定义事件工厂
class MyEventFactory implements EventFactory<Event> {
    @Override
    public Event newInstance() {
        return new Event();
    }
}

// 定义事件处理器
class EventHandler1 implements EventHandler<Event> {
    @Override
    public void onEvent(Event event, long sequence, boolean endOfBatch) {
        System.out.println("Handler1: " + event.getValue());
    }
}

class EventHandler2 implements EventHandler<Event> {
    @Override
    public void onEvent(Event event, long sequence, boolean endOfBatch) {
        System.out.println("Handler2: " + event.getValue());
    }
}

class EventHandler3 implements EventHandler<Event> {
    @Override
    public void onEvent(Event event, long sequence, boolean endOfBatch) {
        System.out.println("Handler3: " + event.getValue());
    }
}

class EventHandler4 implements EventHandler<Event> {
    @Override
    public void onEvent(Event event, long sequence, boolean endOfBatch) {
        System.out.println("Handler4: " + event.getValue());
    }
}

public class DisruptorExample {
    public static void main(String[] args) throws InterruptedException {
        // 创建事件工厂
        EventFactory factory = new MyEventFactory();

        // 创建环形缓冲区
        int bufferSize = 1024;
        Disruptor<Event> disruptor = new Disruptor<>(factory, bufferSize, Executors.defaultThreadFactory(), ProducerType.MULTI, new BlockingWaitStrategy());

        // 创建消费者组
        EventHandler1 handler1 = new EventHandler1();
        EventHandler2 handler2 = new EventHandler2();
        EventHandler3 handler3 = new EventHandler3();
        EventHandler4 handler4 = new EventHandler4();

        // 创建序列屏障
        SequenceBarrier sequenceBarrier1 = disruptor.getRingBuffer().newBarrier();
        SequenceBarrier sequenceBarrier2 = disruptor.getRingBuffer().newBarrier();

        // 创建消费者处理器
        BatchEventProcessor<Event> group1Consumer1 = new BatchEventProcessor<>(disruptor.getRingBuffer(), sequenceBarrier1, handler1);
        BatchEventProcessor<Event> group1Consumer2 = new BatchEventProcessor<>(disruptor.getRingBuffer(), sequenceBarrier1, handler2);

        BatchEventProcessor<Event> group2Consumer1 = new BatchEventProcessor<>(disruptor.getRingBuffer(), sequenceBarrier2, handler3);
        BatchEventProcessor<Event> group2Consumer2 = new BatchEventProcessor<>(disruptor.getRingBuffer(), sequenceBarrier2, handler4);

        // 将消费者的序列添加到环形缓冲区中
        disruptor.getRingBuffer().addGatingSequences(group1Consumer1.getSequence(), group1Consumer2.getSequence(), group2Consumer1.getSequence(), group2Consumer2.getSequence());

        // 启动消费者
        ExecutorService executor = Executors.newFixedThreadPool(4);
        executor.submit(group1Consumer1);
        executor.submit(group1Consumer2);
        executor.submit(group2Consumer1);
        executor.submit(group2Consumer2);

        // 启动 Disruptor
        disruptor.start();

        // 发布事件
        RingBuffer<Event> ringBuffer = disruptor.getRingBuffer();
        for (long i = 0; i < 10; i++) {
            long sequence = ringBuffer.next();
            try {
                Event event = ringBuffer.get(sequence);
                event.setValue(i);
            } finally {
                ringBuffer.publish(sequence);
            }
        }

        // 关闭 Disruptor
        Thread.sleep(1000);
        disruptor.shutdown();
        executor.shutdown();
    }
}
