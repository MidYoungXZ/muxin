package com.muxin.demo.disruptor.demo2;

/**
 * @projectname: muxin
 * @filename: OrderProducer
 * @author: yangxz
 * @data:2024/3/21 17:28
 * @description:
 */

import com.lmax.disruptor.RingBuffer;

public class OrderProducer {

    private RingBuffer<Order> ringBuffer;

    public OrderProducer(
            RingBuffer<Order> ringBuffer) {
        this.ringBuffer = ringBuffer;
    }

    /**
     * 生产数据
     */
    public void setData(String data) {
//        try {
//            Thread.sleep(10);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        // 1、获取待生产下一个的序号
        long sequeuence = ringBuffer.next();
        try {
            // 2、获取下一个待生产的 数据（空）
            Order order = ringBuffer.get(sequeuence);
            // 3、填充产品数据
            order.setId(data);
            System.out.println("生产者 生产-->" + order);
        } finally {
            // 4、发布产品。消费者可以去消费。
            ringBuffer.publish(sequeuence);
        }

    }
}
