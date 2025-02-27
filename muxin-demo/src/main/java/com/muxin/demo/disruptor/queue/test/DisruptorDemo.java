//package com.muxin.demo.disruptor.queue.test;
//
//import com.lmax.disruptor.*;
//import com.lmax.disruptor.dsl.Disruptor;
//import com.lmax.disruptor.dsl.ProducerType;
//import com.muxin.demo.disruptor.demo2.Order;
//import com.muxin.demo.disruptor.demo2.OrderExceptionHandler;
//import com.muxin.demo.disruptor.demo2.OrderProducer;
//import com.muxin.demo.disruptor.demo2.OrderWorkHandler;
//import com.sun.org.apache.bcel.internal.generic.NEW;
//
//import java.util.UUID;
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
//import java.util.concurrent.ThreadFactory;
//import java.util.concurrent.ThreadPoolExecutor;
//
//public class DisruptorDemo {
//
//    public static void main(String[] args) {
//        long l = System.currentTimeMillis();
//        // 环形数组大小
//        int ringBufferSize = 1024 * 32;
//
//        // 创建一个线程工厂提供线程来触发Consumer的事件处理
//        RingBuffer<Order> ringBuffer = RingBuffer
//                .createMultiProducer(() -> new Order(), ringBufferSize, new YieldingWaitStrategy());
//
//        // 通过ringBuffer创建一个屏障
//        SequenceBarrier barrier = ringBuffer.newBarrier();
//
//        // 创建多个消费者
//        ConsumerCase[] workHandlers = new ConsumerCase[4];
//        for (int i = 0; i < workHandlers.length; i++) {
//            workHandlers[i] = new ConsumerCase();
//        }
//
//        // 创建工作池
//        WorkerPool workerPool = new WorkerPool<>(ringBuffer, barrier,
//                new ExceptionHandler() {
//                    @Override
//                    public void handleEventException(Throwable ex, long sequence, Object event) {
//
//                    }
//
//                    @Override
//                    public void handleOnStartException(Throwable ex) {
//
//                    }
//
//                    @Override
//                    public void handleOnShutdownException(Throwable ex) {
//
//                    }
//                }, (WorkHandler) workHandlers);
//
//        // 创建消费者线程池（创建16个线程的线程池，每个消费者可以对应一个线程）
//        ExecutorService threadPool = Executors.newFixedThreadPool(workHandlers.length);
//
//        // 启动工作池（此时我们的队列就在允许中了...消费者也在等待数据消费）
//        workerPool.start(threadPool);
//
//        // 模拟100个生产者，每个生产者生产100条消息
//        for (int i = 1; i <= 4; i++) {
//            OrderProducer orderProducer = new OrderProducer(ringBuffer);
//            for (int j = 1; j <= 100000; j++) {
//                orderProducer.setData(UUID.randomUUID().toString());
//            }
//        }
//        long l2 = System.currentTimeMillis();
//        System.out.println("cost "+(l2-l));
//        System.exit(0);
//
//
//
//
//
//
//        int ringBufferSize = 65536;
//        // 1、自定义 ThreadFactory
//        ThreadFactory threadFactory = Executors.defaultThreadFactory();
//        // 2、Disruptor 构造函数
//        final Disruptor<DataCase> disruptor = new Disruptor<>(
//                DataCase::new,
//                // 2.1 定义环形数组的大小
//                ringBufferSize,
//                threadFactory,
//                // 2.2 单生产者模式
//                ProducerType.SINGLE,
//                // 2.3 CPU饱和的模式
//                new YieldingWaitStrategy()
//        );
//
//        // 3、处理器
//        ConsumerCase consumerCase = new ConsumerCase();
//        disruptor.handleEventsWith(consumerCase);
//        disruptor.start();
//
//        // 创建工作池
//        WorkerPool<Order> workerPool = new WorkerPool<>(ringBuffer, barrier,
//                new OrderExceptionHandler(), workHandlers);
//
//        // 创建消费者线程池（创建16个线程的线程池，每个消费者可以对应一个线程）
//        ExecutorService threadPool = Executors.newFixedThreadPool(workHandlers.length);
//
//        // 启动工作池（此时我们的队列就在允许中了...消费者也在等待数据消费）
//        workerPool.start(threadPool);
//
//        // 4、手工创建线程池
//        ThreadPoolExecutor pool =  (ThreadPoolExecutor)Executors.newFixedThreadPool(20);
//
//        // 5、提交线程池
//        pool.submit(()->{
//            RingBuffer<DataCase> ringBuffer = disruptor.getRingBuffer();
//            for (long i = 0; i < Constants.EVENT_NUM_OHM; i++) {
//                long seq = ringBuffer.next();
//                DataCase data = ringBuffer.get(seq);
//                data.setId(i);
//                data.setName("c" + i);
//                ringBuffer.publish(seq);
//            }
//        });
//
//        pool.shutdown();
//    }
//
//}
//
//class ConsumerCase implements EventHandler<DataCase> {
//    private long startTime;
//    private int i;
//
//    public ConsumerCase() {
//        this.startTime = System.currentTimeMillis();
//    }
//
//    @Override
//    public void onEvent(DataCase event, long sequence, boolean endOfBatch) throws Exception {
//        i++;
//        if (i == Constants.EVENT_NUM_OHM) {
//            long endTime = System.currentTimeMillis();
//            System.out.println("Disruptor costTime = " + (endTime - startTime) + "ms");
//        }
//    }
//}