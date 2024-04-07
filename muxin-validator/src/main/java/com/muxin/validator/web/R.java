package com.muxin.validator.web;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Projectname: muxin
 * @Filename: R
 * @Author: yangxz
 * @Data:2023/11/22 15:48
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class R {

    private String msg;

    public static R fail(String message){
        return new R(message);
    }
}
