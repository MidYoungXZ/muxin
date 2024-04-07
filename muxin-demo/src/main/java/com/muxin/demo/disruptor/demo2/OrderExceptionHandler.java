package com.muxin.demo.disruptor.demo2;

/**
 * @projectname: muxin
 * @filename: OrderExceptionHandler
 * @author: yangxz
 * @data:2024/3/21 17:29
 * @description:
 */

import com.lmax.disruptor.ExceptionHandler;

/**
 * 队列异常时的处理
 */
public class OrderExceptionHandler implements ExceptionHandler<Order> {

    @Override
    public void handleEventException(Throwable throwable, long l, Order order) {

    }

    @Override
    public void handleOnStartException(Throwable throwable) {

    }

    @Override
    public void handleOnShutdownException(Throwable throwable) {

    }
}
