package com.muxin.system.ann;

import java.lang.annotation.*;

/**
 * @Projectname: muxin
 * @Filename: SystemConfig
 * @Author: yangxz
 * @Data:2022/11/11 21:27
 * @Description: TODO
 */
@Target(ElementType.TYPE)
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface CustomIdSeq {
    String seqKey();
}
