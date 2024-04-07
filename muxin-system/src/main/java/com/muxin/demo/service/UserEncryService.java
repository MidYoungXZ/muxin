package com.muxin.demo.service;


import com.muxin.demo.pojo.dto.UserEncrpyDto;

public interface UserEncryService {

    UserEncrpyDto getById(Long id);

    boolean addUser(UserEncrpyDto userDto);

}
