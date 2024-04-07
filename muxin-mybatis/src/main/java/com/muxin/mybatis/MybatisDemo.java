package com.muxin.mybatis;

import com.muxin.mybatis.entity.User;
import com.muxin.mybatis.mapper.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * @Projectname: muxin
 * @Filename: MybatisDemo
 * @Author: yangxz
 * @Data:2023/11/9 10:39
 * @Description:
 */
public class MybatisDemo {
    public static final String URL = "jdbc:mysql://localhost:3306/mblog?useUnicode=true";
    public static final String USER = "root";
    public static final String PASSWORD = "123456";

    public static void main(String[] args) {
        String resource = "conf/mybatis-config.xml";
        InputStream inputStream = null;
        SqlSession sqlSession = null;
        try {
            //读取mybatis-config.xml
            inputStream = Resources.getResourceAsStream(resource);
            //解析mybatis-config.xml配置文件，创建sqlSessionFactory
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            //创建sqlSession
            sqlSession = sqlSessionFactory.openSession();
            //创建userMapper对象（UserMapper并没有实现类）
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            //调用userMapper对象的方法
            User user = userMapper.selectById(1);
            System.out.println(user);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //关闭资源
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            sqlSession.close();
        }
    }
}