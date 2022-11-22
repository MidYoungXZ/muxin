package com.muxin.common.core.enums;

import com.muxin.common.core.common.IResult;
import lombok.Getter;

/**
 * 定义枚举 ，常用返回结果
 */
@Getter
public enum ResultEnum implements IResult {

    SUCCESS(2001, "接口调用成功",true),
    VALIDATE_FAILED(2002, "参数校验失败",false),
    COMMON_FAILED(2003, "接口调用失败",false),
    FORBIDDEN(2004, "没有权限访问资源",false);

    private Integer code;
    private String message;

    private boolean success;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
    ResultEnum(Integer code, String message,boolean success) {
        this.code = code;
        this.message = message;
        this.success = success;
    }

}
