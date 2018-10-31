package com.blackey.common.aop;

import cn.hutool.core.util.NetUtil;
import com.blackey.common.result.Result;
import com.blackey.common.utils.DesensitizedUtils;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 请求参数日志aop
 * 请求参数支持自定义对象脱敏
 * 自定义对象必须实现序列化
 * 响应参数必须定义为Result，将对象封装在data中
 * Created by Kaven
 * Date: 2018/5/21
 */
@Aspect
public class RequestLogAop {

    private static final Logger LOGGER = LoggerFactory.getLogger(RequestLogAop.class);

    private static final LocalVariableTableParameterNameDiscoverer parameterNameDiscoverer = new LocalVariableTableParameterNameDiscoverer();

    private ThreadLocal<Long> startTime = new ThreadLocal<>();

    @Pointcut("execution(public * com.blackey..rest..*.*(..)) || " +
            "execution(public * com.blackey..controller..*.*(..))")
    public void webLog(){}

    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint){

        startTime.set(System.currentTimeMillis());

        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        if(request.getRequestURL().equals("/sys/login")){
            return;
        }

        String method = request.getMethod();

        // 记录下请求内容
        LOGGER.info("URL : " + request.getRequestURL().toString());
        LOGGER.info("HTTP_METHOD : " + method);
        LOGGER.info("IP : " +  NetUtil.getIpByHost(NetUtil.getLocalhostStr()));
        LOGGER.info("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());

        // 获得切入的方法
        Method httpMethod = ((MethodSignature)joinPoint.getSignature()).getMethod();
        String[] parameterNames = parameterNameDiscoverer.getParameterNames(httpMethod);
        Map<String,Object> map = new HashMap<>();

        for (int i=0;i<parameterNames.length;i++){
            Object object = joinPoint.getArgs()[i];
            //排除
            if((object instanceof HttpServletRequest)
                    || (object instanceof HttpServletResponse)
                    || (object instanceof MultipartFile)){
                continue;
            }


            //基本类型、string、枚举,直接保存在map中
            if(isJDKType(object.getClass()) || (object instanceof String) ||
                    (object.getClass().isPrimitive()) ||
                    (object.getClass().isEnum())){
                map.put(parameterNames[i],object);
            }else {//用户自定义对象,map,集合
                String s = DesensitizedUtils.getJson(object);
                map.put(parameterNames[i],s);
                LOGGER.info("Desensitized REQUEST ARGS : " + parameterNames[i]+":"+s);
            }
        }
        LOGGER.info("REQUEST ARGS ---: " + map.toString());

    }

    @AfterReturning(returning = "ret", pointcut = "webLog()")
    public void doAfterReturning(Object ret){
        // 处理完请求，返回内容
        if(null != ret){
            if(ret instanceof Result){
                LOGGER.info("RESPONSE ARGS : " + DesensitizedUtils.getJson(ret));
            }
        }else{
            LOGGER.info("RESPONSE : " + null);
        }
        LOGGER.info("SPEND TIME : " + (System.currentTimeMillis() - startTime.get()));

    }


    /**
     * 是否jdk类型变量
     *
     * @param clazz class
     * @return boolean
     */
    private static boolean isJDKType(Class clazz){
        //Class clazz = field.get(objSource).getClass();
        return clazz.getPackage() != null && (StringUtils.startsWith(clazz.getPackage().getName(), "javax.")
                || StringUtils.startsWith(clazz.getPackage().getName(), "java.")
                || StringUtils.startsWith(clazz.getName(), "javax.")
                || StringUtils.startsWith(clazz.getName(), "java."));
    }

}
