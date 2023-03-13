package com.muxin.system.service;


import com.muxin.system.dto.UserEncrpyDto;

public interface UserEncryService {

    UserEncrpyDto getById(Long id);

    boolean addUser(UserEncrpyDto userDto);

}
