package com.muxin.demo.disruptor.queue.test;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @projectname: muxin
 * @filename: DataCase
 * @author: yangxz
 * @data:2024/7/24 14:20
 * @description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DataCase implements Serializable {
    private Long id ;
    private String name;
}