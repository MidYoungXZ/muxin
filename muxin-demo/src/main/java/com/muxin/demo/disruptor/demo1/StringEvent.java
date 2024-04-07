package com.muxin.demo.disruptor.demo1;

/**
 * @projectname: muxin
 * @filename: StringEvent
 * @author: yangxz
 * @data:2024/3/21 17:16
 * @description:
 */

import lombok.Data;

@Data
/**
 * 放在队列中的对象(数据)
 */
public class StringEvent {

    private String value;

}
