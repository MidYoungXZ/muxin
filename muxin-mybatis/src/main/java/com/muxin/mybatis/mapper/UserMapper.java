package com.muxin.mybatis.mapper;

import com.muxin.mybatis.entity.User;

/**
 * @Projectname: muxin
 * @Filename: UserMapper
 * @Author: yangxz
 * @Data:2023/11/9 10:36
 * @Description:
 */
public interface UserMapper {
    User selectById(Integer id);
}