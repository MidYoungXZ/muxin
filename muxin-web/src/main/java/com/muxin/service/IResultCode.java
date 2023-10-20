package com.muxin.service;

import java.io.Serializable;

/**
 * @Projectname: bsfit-rgas-parent
 * @Filename: IResultCode
 * @Author: yangxz
 * @Data:2023/3/29 14:03
 * @Description:
 */
public interface IResultCode extends Serializable {

    /**
     * 消息
     *
     * @return String
     */
    String getMsg();

    /**
     * 状态码
     *
     * @return int
     */
    String getCode();

}