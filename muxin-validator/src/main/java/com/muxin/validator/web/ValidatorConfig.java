package com.muxin.validator.web;

import org.hibernate.validator.HibernateValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

/**
 * @Projectname: muxin
 * @Filename: ValidatorConfig
 * @Author: yangxz
 * @Data:2023/11/22 15:43
 * @Description:
 */
@Configuration
public class ValidatorConfig {

    /**
     * 配置验证器
     * @return validator
     */
    @Bean
    public Validator validator() {
        ValidatorFactory validatorFactory = Validation.byProvider(HibernateValidator.class)
                .configure()
                // 快速失败模式
                .failFast(true)
                // .addProperty( "hibernate.validator.fail_fast", "true" )
                .buildValidatorFactory();
        return validatorFactory.getValidator();
    }
}
