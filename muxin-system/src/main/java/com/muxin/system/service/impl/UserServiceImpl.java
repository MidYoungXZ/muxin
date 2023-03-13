package com.muxin.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.muxin.system.entity.User;
import com.muxin.system.mapper.UserMapper;
import com.muxin.system.dto.UserDto;
import com.muxin.system.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.muxin.system.service.OtherService;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yangxz
 * @since 2022-11-08
 */
//@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService, OtherService {


    @Override
    public UserDto getById(Long id) {
        if (id != null && id > 0) {
            return new UserDto(id, "默认名字");
        }
        QueryWrapper<User> wrapper = new QueryWrapper<>();

        throw new RuntimeException("参数异常");
    }

    @Override
    public String getUserName(Long id) {
        if (id != null && id > 0) {
            return "默认名字";
        }
        throw new RuntimeException("参数异常");
    }

    @Override
    public boolean addUser(UserDto userDto) {
        if (userDto != null) {
            if (userDto.getUsername() != null) {
                return true;
            }
        }
        throw new RuntimeException("参数异常");
    }

    @Override
    public List<UserDto> getList() {
        ArrayList<UserDto> list = new ArrayList<>();
        list.add(new UserDto(1L, "1默认名字"));
        list.add(new UserDto(2L, "2默认名字"));
        list.add(new UserDto(3L, "3默认名字"));
        return list;
    }
}
