package com.muxin.system;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.muxin.MuXinApplication;
import com.muxin.system.entity.RgLabel;
import com.muxin.system.entity.SysUser;
import com.muxin.system.manager.IRgLabelService;
import com.muxin.system.manager.ISysUserService;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.el.util.ReflectionUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.sql.Connection;
import java.util.Date;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = {MuXinApplication.class})
public class SpringTest {


    @Resource
    private ISysUserService iSysUserService;
    @Autowired
    private HikariDataSource dataSource;
    @Autowired
    private IRgLabelService iRgLabelService;

    @Autowired
    private ConfigurableEnvironment environment;




    @Test
    public void testMpSelect() {
//        iRgLabelService.list().forEach(System.out::println);

        System.err.println(environment.getProperty("mybatis-plus.global-config.db-config.id-type", IdType.class));
//        System.err.println(iKeyGenerator.getClass());


        RgLabel label = new RgLabel();
        label.setLabelGroupId(88L);
        label.setUpdater("admin");
        label.setCreater("admin");
        label.setGraphId(33L);
        label.setLabelCode("my");
        label.setDim("test2");
        label.setLabelName("测试的");
        label.setGraphId(44L);
        label.setCreateTime(new Date());
        label.setUpdateTime(new Date());

        System.out.println(iRgLabelService.save(label));


        iRgLabelService.list().forEach(System.out::println);

    }


    @Test
    public void page(){
        QueryWrapper<SysUser> wrapper = new QueryWrapper<>();
        wrapper.eq("USER_NAME","test");
        Page<SysUser> page = new Page<>(1,5);
        Page<SysUser> userPage = iSysUserService.page(page, wrapper);
        System.out.println(userPage.getRecords().size());
    }


    @Test
    public void page2(){
        QueryWrapper<SysUser> wrapper = new QueryWrapper<>();
//        wrapper.lambda().eq(SysUser::getUserName,"test");
        Page<SysUser> page = new Page<>(1,5);
        Page<SysUser> userPage = iSysUserService.page(page, wrapper);
        System.out.println(userPage.getRecords().size());
    }
    @Test
    public void pageLambda(){
        Page<SysUser> page = new Page<>(1,5);
//        Page<SysUser> page1 = iSysUserService.lambdaQuery().eq(SysUser::getUsername, "test").page(page);
        QueryWrapper<SysUser> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(SysUser::getUsername, "admin");
        wrapper.eq("USERNAME","admin");
        iSysUserService.page(page,wrapper);
        System.out.println(page.getRecords().size());
    }


    @Test
    public void selectDB() throws Exception{
//        System.out.println(mapper.selectMy());
//        String s = sysUserMapper.selectDB();
//        System.out.println(s);

        Connection connection = dataSource.getConnection();
        System.out.println(connection.getClass().getName());


    }

    public static void main(String[] args)throws ClassNotFoundException  {
        System.out.println(ReflectionUtil.forName("com.muxin.system.service.CustomIdGenerator"));

    }
}