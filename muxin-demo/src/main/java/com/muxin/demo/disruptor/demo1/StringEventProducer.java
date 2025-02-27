package com.muxin.demo.disruptor.demo1;

/**
 * @projectname: muxin
 * @filename: StringEventProducer
 * @author: yangxz
 * @data:2024/3/21 17:16
 * @description:
 */
import com.lmax.disruptor.RingBuffer;

public class StringEventProducer {

    private RingBuffer<StringEvent> ringBuffer;

    public StringEventProducer(
            RingBuffer<StringEvent> ringBuffer) {
        this.ringBuffer = ringBuffer;
    }

    /**
     * 生产数据
     */
    public void setData(String data) {
        try {
            Thread.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 1、获取待生产下一个的序号
        long sequeuence = ringBuffer.next();
        try {
            // 2、获取下一个待生产的 数据（空）
            StringEvent stringEvent = ringBuffer.get(sequeuence);
            // 3、填充产品数据
            stringEvent.setValue(data);
            System.out.println("生产-->" + data);
        } finally {
            // 4、发布产品。消费者可以去消费。
            ringBuffer.publish(sequeuence);
        }

    }
}
