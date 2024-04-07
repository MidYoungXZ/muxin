package com.muxin.validator.web;

/**
 * @Projectname: muxin
 * @Filename: CommmonGlobalExceptionHandler
 * @Author: yangxz
 * @Data:2023/11/22 15:47
 * @Description:
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class CommmonGlobalExceptionHandler {

    public static final Logger LOG = LoggerFactory.getLogger(CommmonGlobalExceptionHandler.class);

    /**
     * hibernate validator 数据绑定验证异常拦截
     *
     * @param e 绑定验证异常
     * @return 错误返回消息
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public R validateErrorHandler(MethodArgumentNotValidException e) {
        ObjectError error = e.getBindingResult().getAllErrors().get(0);
        LOG.info("数据验证异常：{}", error.getDefaultMessage());
        return R.fail(error.getDefaultMessage());
    }

}
