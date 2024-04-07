package com.muxin.demo.service.impl;

import com.muxin.demo.pojo.entity.User;
import com.muxin.demo.mapper.UserMapper;
import com.muxin.demo.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author muxin
 * @since 2023-03-15
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
