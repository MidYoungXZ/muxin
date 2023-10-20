package com.muxin.pojo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @Projectname: muxin
 * @Filename: TestStaticObj
 * @Author: yangxz
 * @Data:2023/10/17 14:08
 * @Description:
 */
@Component
@ConfigurationProperties(
        prefix = "test"
)
@Data
public class TestStaticObj {

    public static String name;
    public static String age;
}
