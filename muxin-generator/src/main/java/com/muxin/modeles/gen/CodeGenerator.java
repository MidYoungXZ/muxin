package com.muxin.modeles.gen;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.Collections;

/**
 * @Projectname: muxin
 * @Filename: Mian
 * @Author: yangxz
 * @Data:2022/11/8 18:20
 * @Description: TODO
 */
// 演示例子，执行 main 方法控制台输入模块表名回车自动生成对应项目目录中
public class CodeGenerator {

    public static void main(String[] args) {
        FastAutoGenerator.create("jdbc:oracle:thin:@47.98.248.203:1521/helowin", "MUXIN2", "muxin2")
                .globalConfig(builder -> {
                    builder.author("muxin") // 设置作者
                            .enableSwagger() // 开启 swagger 模式
                            .fileOverride() // 覆盖已生成文件
                            .outputDir("/Users/midyoung/bsfit/study/muxin"); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("com.muxin") // 设置父包名
                            .moduleName("system") // 设置父包模块名
                            .pathInfo(Collections.singletonMap(OutputFile.mapperXml, "/Users/midyoung/bsfit/study/muxin")); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.addInclude("SYSTEM_USER"); // 设置需要生成的表名
//                            .addTablePrefix("t_", "c_"); // 设置过滤表前缀
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }

}
