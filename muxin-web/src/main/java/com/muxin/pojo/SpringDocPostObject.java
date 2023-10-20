package com.muxin.pojo;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.ToString;

/**
 * @Projectname: muxin
 * @Filename: SpringDocPostObject
 * @Author: yangxz
 * @Data:2023/10/17 11:21
 * @Description:
 */
@Schema(name = "post请求实体")
@Data
@ToString
public class SpringDocPostObject {

    @Schema(description = "id")
    private String id;
    @Schema(description = "用户名")
    private String name;
    @Schema(description = "年龄")
    private Integer age;

}
