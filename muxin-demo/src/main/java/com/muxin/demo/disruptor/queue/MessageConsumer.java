package com.muxin.demo.disruptor.queue;

import com.alibaba.fastjson2.JSONObject;

import java.util.Collection;

/**
 * @projectname: muxin
 * @filename: MessageConsmer
 * @author: yangxz
 * @data:2024/7/24 11:20
 * @description:
 */
public interface MessageConsumer {

    Collection<String> consume();

    Collection<String> consume(String topic);

    <T> Collection<T> consume(String topic, Class<T> objectClass);


    default void ii(){
        Object parse = JSONObject.parseObject("uu", MessageConsumer.class);
    }

}
