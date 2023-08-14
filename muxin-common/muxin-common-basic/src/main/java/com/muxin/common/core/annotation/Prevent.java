package com.muxin.common.core.annotation;


import com.muxin.common.core.enums.PreventStrategyEnum;

import java.lang.annotation.*;

/**
 * 接口防刷注解
 * 使用：
 * 在相应需要防刷的方法上加上
 * 该注解，即可
 */
@Documented
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Prevent {

    /**
     * 限制的时间值（秒）
     *
     * @return
     */
    String value() default "60";

    /**
     * 提示
     */
    String message() default "";

    /**
     * 策略
     *
     * @return
     */
    PreventStrategyEnum strategy() default PreventStrategyEnum.DEFAULT;
}