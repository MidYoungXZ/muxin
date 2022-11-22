package com.muxin.common.core.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * @author: yangxz
 * @create: 2022/9/21
 * @Description:
 */
@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {
    /**
     * 交换MappingJackson2HttpMessageConverter与第一位元素
     * 让返回值类型为String的接口能正常返回包装结果
     *
     * @param converters initially an empty list of converters
     */
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        for (int i = 0; i < converters.size(); i++) {
            if (converters.get(i) instanceof MappingJackson2HttpMessageConverter) {
                MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = (MappingJackson2HttpMessageConverter) converters.get(i);
                // 第一位的放在这一位
                converters.set(i, converters.get(0));
                //放到第一位，互换位置
                converters.set(0, mappingJackson2HttpMessageConverter);
                break;
            }
        }
    }
}
