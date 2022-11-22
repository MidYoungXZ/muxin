package com.muxin.common.core.handler;

import com.muxin.common.core.annotation.NotControllerResponseAdvice;
import com.muxin.common.core.common.Result;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * @author: yangxz
 * @create: 2022/9/21
 * @Description: 进行 统一包装的工作
 * Spring 中提供了一个类  ResponseBodyAdvice
 * 对 Controller 返回的内容在 HttpMessageConverter 进行类型转换之前拦截，
 * 进行相应的处理操作后，再将结果返回给客户端
 */
// 如果引入了swagger或knife4j的文档生成组件，这里需要仅扫描自己项目的包，否则文档无法正常生成
@RestControllerAdvice(basePackages = "com.muxin.demo")
public class ResponseAdvice implements ResponseBodyAdvice<Object> {
    /**
     * 判断是否要交给 beforeBodyWrite 方法执行，ture：需要；false：不需要
     */
    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        // 如果不需要进行封装的，可以添加一些校验手段，比如添加标记排除的注解
//        return true;
        //response是Result类型，
        return !(returnType.getParameterType().isAssignableFrom(Result.class)
                //或者注释了NotControllerResponseAdvice都不进行包装
                || returnType.hasMethodAnnotation(NotControllerResponseAdvice.class));
    }


    /**
     * 对 response 进行具体的处理
     */
    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        //如果body已经被包装了，就不进行包装
        if (body instanceof Result) {
            return body;
        }
//        //是String类型的就特殊处理,手动将Result对象转化为Json字符串
//        if (body instanceof String) {
//            try {
//                return JSONObject.toJSONString(Result.success(body));
//            } catch (Exception e) {
//                throw new RuntimeException(e);
//            }
//        }
        return Result.success(body);
    }
}
