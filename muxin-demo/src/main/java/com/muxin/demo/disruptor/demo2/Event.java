package com.muxin.demo.disruptor.demo2;

/**
 * @projectname: muxin
 * @filename: Event
 * @author: yangxz
 * @data:2024/3/26 11:23
 * @description:
 */
public class Event <T>{


    private Object data;

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
