package com.muxin.demo.service.impl;


import com.muxin.demo.service.UserEncryService;
import com.muxin.demo.pojo.dto.UserEncrpyDto;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author: yangxz
 * @create: 2022/9/21
 * @Description:
 */
//@Service
public class UserEncryServiceImpl implements UserEncryService {

    private static ConcurrentHashMap<Long, UserEncrpyDto> map = new ConcurrentHashMap<>();

    //使用加密注解
    @Override
    public UserEncrpyDto getById(Long id) {
        if (id != null && id > 0) {
            UserEncrpyDto userDto = map.get(id);
            System.out.println("service 查到的：" + userDto.getPassword());
            return userDto;
        }
        throw new RuntimeException("参数异常");
    }

    //使用加密注解
    @Override
    public boolean addUser(UserEncrpyDto userDto) {
        if (userDto != null) {
            System.out.println("service传递进来的参数是:" + userDto.toString());
            if (userDto.getUsername() != null) {
                map.put(1L, userDto);
                System.out.println("DB存的数据:" + userDto.toString());
                return true;
            }
        }
        throw new RuntimeException("参数异常");
    }
}
