package com.muxin.commn.openapi;

/**
 * @Projectname: muxin
 * @Filename: SpringDocConfig
 * @Author: yangxz
 * @Data:2023/10/16 17:46
 * @Description:
 */

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * SpringDoc API文档相关配置
 * Created by macro on 2022/3/4.
 */

/**
 * SpringDoc API 文档相关配置
 */
@Configuration
public class SpringDocConfig {

    @Bean
    public OpenAPI springOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("SpringDoc API Test")
                        .description("SpringDoc Simple Application Test")
                        .version("v0.0.21")
                        .license(new License().name("Apache 2.0").url("https://www.apache.org/licenses/LICENSE-2.0")))
                .externalDocs(new ExternalDocumentation()
                        .description("替代 Springfox 的 SpringDOC 入门 文档")
                        .url("https://www.cnblogs.com/jddreams/p/15922674.html"));
    }

}

