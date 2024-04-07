package com.muxin.mybatis.entity;

import lombok.Data;

/**
 * @Projectname: muxin
 * @Filename: User
 * @Author: yangxz
 * @Data:2023/11/9 10:35
 * @Description:
 */
@Data
public class User {
    private Integer id;
    private String userName;
    private Integer age;
    private Integer gender;

}