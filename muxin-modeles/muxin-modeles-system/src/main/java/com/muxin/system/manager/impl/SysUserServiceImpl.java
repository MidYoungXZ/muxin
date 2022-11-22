package com.muxin.system.manager.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.muxin.system.entity.SysUser;
import com.muxin.system.manager.ISysUserService;
import com.muxin.system.mapper.SysUserMapper;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author 邦盛科技
 * @since 2022-11-14
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {

}
