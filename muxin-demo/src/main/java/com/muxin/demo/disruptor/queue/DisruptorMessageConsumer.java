package com.muxin.demo.disruptor.queue;

import java.util.Collection;

/**
 * @projectname: muxin
 * @filename: DisruptorMessageConsumer
 * @author: yangxz
 * @data:2024/7/30 10:02
 * @description:
 */
public class DisruptorMessageConsumer implements MessageConsumer{


    @Override
    public Collection<String> consume() {
        return null;
    }

    @Override
    public Collection<String> consume(String topic) {
        return null;
    }

    @Override
    public <T> Collection<T> consume(String topic, Class<T> objectClass) {
        return null;
    }
}
