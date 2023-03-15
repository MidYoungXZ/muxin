//package com.muxin.common.core.aop;
//
//
//
//import com.alibaba.fastjson2.JSON;
//import com.muxin.common.core.annotation.Prevent;
//import com.muxin.common.core.enums.PreventStrategyEnum;
//import com.muxin.common.core.exception.BusinessException;
//import lombok.extern.slf4j.Slf4j;
//
//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Pointcut;
//import org.aspectj.lang.reflect.MethodSignature;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.util.StringUtils;
//
//import javax.servlet.http.HttpServletRequest;
//import java.lang.reflect.Method;
//import java.util.Base64;
//
///**
// * 大致逻辑为：
// * 定义一切面，通过@Prevent注解作为切入点、在该切面的前置通知获取该方法的所有入参并将其Base64编码，
// * 将入参Base64编码+完整方法名作为redis的key，入参作为reids的value，@Prevent的value作为redis的expire，存入redis；
// * 每次进来这个切面根据入参Base64编码+完整方法名判断redis值是否存在，
// * 存在则拦截防刷，不存在则允许调用；
// */
//@Aspect
//@Component
//@Slf4j
//public class PreventAop {
//
//    @Autowired
////    private RedisUtil redisUtil;
//
//    /**
//     * 切入点
//     */
//    @Pointcut("@annotation(com.muxin.common.core.annotation.Prevent)")
//    public void pointcut() {
//    }
//
//    /**
//     * 处理前
//     *
//     * @return
//     */
////    @Before("pointcut()")
//    public void joinPoint(JoinPoint joinPoint) throws Exception {
//        //获取ip
//        HttpServletRequest request = (HttpServletRequest) joinPoint.getArgs()[0];
//        String ip = getIp(request);
//        System.out.println("请求的：" + ip);
//        //获取请求参数
//        String requestStr = null;
//        //如果入参个数 !=1
//        if (joinPoint.getArgs().length != 1) {
//            for (int i = 1; i < joinPoint.getArgs().length; i++) {
//                requestStr += JSON.toJSONString(joinPoint.getArgs()[i]);
//            }
//            if (StringUtils.isEmpty(requestStr) || requestStr.equalsIgnoreCase("{}")) {
//                throw new BusinessException("[防刷]入参不允许为空");
//            }
//        }
//
//
//        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
//        Method method = joinPoint.getTarget().getClass().getMethod(methodSignature.getName(),
//                methodSignature.getParameterTypes());
//
//        Prevent preventAnnotation = method.getAnnotation(Prevent.class);
//        String methodFullName = method.getDeclaringClass().getName() + method.getName();
//        entrance(preventAnnotation, ip + requestStr, methodFullName);
//        return;
//    }
//
//    public String getIp(HttpServletRequest request) {
//        String ip = request.getHeader("x-forwarded-for");
//        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
//            ip = request.getHeader("Proxy-Client-IP");
//        }
//        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
//            ip = request.getHeader("WL-Proxy-Client-IP");
//        }
//        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
//            ip = request.getHeader("HTTP_CLIENT_IP");
//        }
//        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
//            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
//        }
//        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
//            ip = request.getRemoteAddr();
//        }
//        return ip;
//    }
//
//    /**
//     * 入口
//     *
//     * @param prevent
//     * @param requestStr
//     */
//    private void entrance(Prevent prevent, String requestStr, String methodFullName) throws Exception {
//        PreventStrategyEnum strategy = prevent.strategy();
//        switch (strategy) {
//            case DEFAULT:
//                defaultHandle(requestStr, prevent, methodFullName);
//                break;
//            default:
//                throw new BusinessException("无效的策略");
//        }
//    }
//
//    /**
//     * 默认处理方式
//     *
//     * @param requestStr
//     * @param prevent
//     */
//    private void defaultHandle(String requestStr, Prevent prevent, String methodFullName) throws Exception {
//        String base64Str = toBase64String(requestStr);
//        long expire = Long.parseLong(prevent.value());
//
//        String resp = redisUtil.get(methodFullName + base64Str);
//        if (StringUtils.isEmpty(resp)) {
//            redisUtil.set(methodFullName + base64Str, requestStr, expire);
//        } else {
//            String message = !StringUtils.isEmpty(prevent.message()) ? prevent.message() : expire + "秒内不允许重复请求";
//            throw new BusinessException(message);
//        }
//    }
//
//    /**
//     * 对象转换为base64字符串
//     *
//     * @param obj 对象值
//     * @return base64字符串
//     */
//    private String toBase64String(String obj) throws Exception {
//        if (StringUtils.isEmpty(obj)) {
//            return null;
//        }
//        Base64.Encoder encoder = Base64.getEncoder();
//        byte[] bytes = obj.getBytes("UTF-8");
//        return encoder.encodeToString(bytes);
//    }
//}