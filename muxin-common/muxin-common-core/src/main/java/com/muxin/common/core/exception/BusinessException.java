package com.muxin.common.core.exception;

/**
 * @author: yangxz
 * @create: 2022/9/21
 * @Description: 自定义异常
 */
public class BusinessException extends RuntimeException {
    public BusinessException(String message) {
        super(message);
    }
}
