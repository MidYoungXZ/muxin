package com.muxin.common.core.common;

/**
 * @author: yangxz
 * @create: 2022/9/21
 * @Description: 定义返回数据结构
 */
public interface IResult {
    Integer getCode();

    String getMessage();

    boolean isSuccess();
}
