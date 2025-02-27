package com.muxin.demo;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;

/**
 * @projectname: muxin
 * @filename: Main
 * @author: yangxz
 * @data:2024/11/7 09:39
 * @description:
 */
public class Main {


    public static void main(String[] args) throws Exception {

        DateTime date = DateUtil.date(1731283956840L);
        DateTime date2 = DateUtil.date(1731283971948L);

        String format = DateUtil.format(date, "yyyy/MM/dd HH:mm:ss SSS");
        String format2 = DateUtil.format(date2, "yyyy/MM/dd HH:mm:ss SSS");

        System.out.println(format);
        System.out.println(format2);
        //2024/11/11 08:12:36 840

        //15.1

        //2024/11/11 08:12:51 940

        //2024/11/11 08:12:50 082



    }


}
