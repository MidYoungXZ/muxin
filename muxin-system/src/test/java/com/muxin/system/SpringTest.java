package com.muxin.system;

import com.muxin.system.entity.User;
import com.muxin.system.service.IUserService;
import org.apache.el.util.ReflectionUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.PipedReader;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = {SystemApplication.class})
public class SpringTest {

    @Autowired
    private IUserService userService;

    @Test
    public void test() {

        User user = new User();
        user.setName("muxin");
        user.setAge(18);
        user.setEmail("wwwwww@163.com");
        userService.save(user);

        userService.list().forEach(System.out::println);
    }




}