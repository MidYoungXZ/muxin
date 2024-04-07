package com.muxin.validator.demo;

import org.hibernate.validator.HibernateValidator;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @Projectname: muxin
 * @Filename: ValidateTest
 * @Author: yangxz
 * @Data:2023/11/22 11:23
 * @Description:
 */
public class ValidateTest {
    //初始化一个校验器工厂
    private static ValidatorFactory validatorFactory = Validation
            .byProvider(HibernateValidator.class)
            .configure()
            //校验失败是否立即返回： true-遇到一个错误立即返回不在往下校验，false-校验完所有字段才返回
            .failFast(false)
            .buildValidatorFactory();
    Validator validator = validatorFactory.getValidator();


    /**
     * 简单对象校验
     */
    @Test
    public void testSimple() {
        Person s=new Person ();
        s.setAge(5);
        s.setName(" ");
        s.setEmail("email");

        Set<ConstraintViolation<Person>> result=validator.validate(s);
        System.out.println("遍历输出错误信息:");
        //getPropertyPath() 获取属性全路径名
        //getMessage() 获取校验后的错误提示信息
        result.forEach(r-> System.out.println(r.getPropertyPath()+":"+r.getMessage()));
    }



    @Test
    public void test() {
        Person p=new Person();
        p.setAge(30);
        p.setName("zhangsan");
        //p.setIsMarried(true);

        Person p2=new Person();
        p2.setAge(30);
        //p2.setName("zhangsan2");
        p2.setIsMarried(false);
        //p2.setHasChild(true);

        Org org=new Org();
        //org.setId(1);

        List<Person> list=new ArrayList<>();
        list.add(p);
        list.add(p2);
        //增加一个null，测试是否会校验元素为null
        list.add(null);

        Employee e=new Employee();
        e.setPeople(list);
        org.setEmployee(e);

        Set<ConstraintViolation<Org>> result=validator.validate(org);
        System.out.println("遍历输出错误信息：");
        result.forEach(r-> System.out.println(r.getPropertyPath()+":"+r.getMessage()));

    }


    @Test
    public void testGroup() {
        People p=new People();
        p.setAge(30);
        p.setName(" ");
        p.setIsMarried(false);

        Set<ConstraintViolation<People>> result;
        //通过isMarried的值来动态指定分组校验
        if(p.getIsMarried()){
            //如果已婚，则按照已婚的分组字段
            result=validator.validate(p, Group.Married.class);
        }else{
            //如果未婚，则只校验未婚的分组字段
            result=validator.validate(p, Group.UnMarried.class);
        }

        System.out.println("遍历输出错误信息：");
        result.forEach(r-> System.out.println(r.getPropertyPath()+":"+r.getMessage()));
    }



}
