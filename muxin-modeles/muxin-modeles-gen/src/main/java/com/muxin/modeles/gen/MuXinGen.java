//package com.muxin.modeles.gen;
//
//import com.baomidou.mybatisplus.generator.FastAutoGenerator;
//import com.baomidou.mybatisplus.generator.config.OutputFile;
//import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
//
//import java.util.Collections;
//
///**
// * @Projectname: muxin
// * @Filename: MuXinGen
// * @Author: yangxz
// * @Data:2022/11/10 17:03
// * @Description: TODO
// */
//public class MuXinGen {
//
//    public static void main(String[] args) {
//        String url="jdbc:mysql://47.98.248.203:3306/muxin?serverTimezone=Asia/Shanghai&characterEncoding=utf-8";
//        FastAutoGenerator.create(url, "root", "root123")
//                .globalConfig(builder -> {
//                    builder.author("rgas") // 设置作者
//                            .enableSwagger() // 开启 swagger 模式
//                            .fileOverride() // 覆盖已生成文件
//                            .outputDir("/Users/midyoung/Desktop/临时/mybatis-plus"); // 指定输出目录
//                })
//                .packageConfig(builder -> {
//                    builder.parent("com.baomidou.mybatisplus.samples.generator") // 设置父包名
//                            .moduleName("system") // 设置父包模块名
//                            .pathInfo(Collections.singletonMap(OutputFile.mapperXml, "/Users/midyoung/Desktop/临时/mybatis-plus/xml")); // 设置mapperXml生成路径
//                })
//                .strategyConfig(builder -> {
//                    builder.addInclude("sys_user");// 设置需要生成的表名
////                            .addTablePrefix("t_", "c_"); // 设置过滤表前缀
//                })
//                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
//                .execute();
//    }
//}
