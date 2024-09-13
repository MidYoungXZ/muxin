package com.muxin.demo.disruptor.queue;

/**
 * @projectname: muxin
 * @filename: MessageProducer
 * @author: yangxz
 * @data:2024/7/24 11:13
 * @description:
 */
public interface MessageProducer {

    void send(String topic, String message);

    void send(String message);



}
