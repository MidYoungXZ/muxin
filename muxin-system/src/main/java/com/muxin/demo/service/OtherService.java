package com.muxin.demo.service;



import com.muxin.demo.pojo.dto.UserDto;

import java.util.List;

/**
 * @author: yangxz
 * @create: 2022/9/21
 * @Description:
 */
public interface OtherService {

    UserDto getById(Long id);

    String getUserName(Long id);

    boolean addUser(UserDto userDto);

    List<UserDto> getList();

}
