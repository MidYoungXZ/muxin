package com.muxin.demo.disruptor.demo2;

/**
 * @projectname: muxin
 * @filename: MainTest
 * @author: yangxz
 * @data:2024/3/21 17:29
 * @description:
 */
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.SequenceBarrier;
import com.lmax.disruptor.WorkerPool;
import com.lmax.disruptor.YieldingWaitStrategy;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainTest {

    /**
     * 多生产者多消费者(竞争关系-非重复消费)
     * @param args
     */
    public static void main(String[] args) {
        // 环形数组大小
        int ringBufferSize = 1024 * 1024;

        // 创建一个线程工厂提供线程来触发Consumer的事件处理
        RingBuffer<Order> ringBuffer = RingBuffer
                .createMultiProducer(() -> new Order(), ringBufferSize, new YieldingWaitStrategy());

        // 通过ringBuffer创建一个屏障
        SequenceBarrier barrier = ringBuffer.newBarrier();

        // 创建多个消费者
        OrderWorkHandler[] workHandlers = new OrderWorkHandler[16];
        for (int i = 0; i < workHandlers.length; i++) {
            workHandlers[i] = new OrderWorkHandler(i);
        }

        // 创建工作池
        WorkerPool<Order> workerPool = new WorkerPool<>(ringBuffer, barrier,
                new OrderExceptionHandler(), workHandlers);

        // 创建消费者线程池（创建16个线程的线程池，每个消费者可以对应一个线程）
        ExecutorService threadPool = Executors.newFixedThreadPool(workHandlers.length);

        // 启动工作池（此时我们的队列就在允许中了...消费者也在等待数据消费）
        workerPool.start(threadPool);

        // 模拟100个生产者，每个生产者生产100条消息
        for (int i = 1; i <= 100; i++) {
            OrderProducer orderProducer = new OrderProducer(ringBuffer);
            for (int j = 1; j <= 100; j++) {
                orderProducer.setData(UUID.randomUUID().toString());
            }
        }
        System.exit(0);
    }
}
