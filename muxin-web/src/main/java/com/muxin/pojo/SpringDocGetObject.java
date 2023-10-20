package com.muxin.pojo;

import io.swagger.v3.oas.annotations.Parameter;
import lombok.Data;
import lombok.ToString;

/**
 * @Projectname: muxin
 * @Filename: SpringDocGetObject
 * @Author: yangxz
 * @Data:2023/10/17 11:19
 * @Description:
 */

@Data
@ToString
public class SpringDocGetObject {

    @Parameter(description = "id")
    private String id;
    @Parameter(description = "用户名")
    private String name;
    @Parameter(description = "年龄")
    private Integer age;

}
