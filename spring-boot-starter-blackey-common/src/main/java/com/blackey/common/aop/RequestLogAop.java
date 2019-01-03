package com.blackey.common.aop;

import cn.hutool.core.util.NetUtil;
import com.alibaba.fastjson.JSON;
import com.blackey.common.result.Result;
import com.blackey.common.utils.DesensitizedUtils;
import com.google.gson.Gson;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.http.HttpMethod;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

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

    private static final String TRACE_ID="traceId",START_TIME="START_TIME";

    private static final Logger LOGGER = LoggerFactory.getLogger(RequestLogAop.class);

    private static final LocalVariableTableParameterNameDiscoverer parameterNameDiscoverer = new LocalVariableTableParameterNameDiscoverer();

    private ThreadLocal<Long> startTime = new ThreadLocal<>();

    @Pointcut("execution(public * com.blackey..rest..*.*(..)) || " +
            "execution(public * com.blackey..controller..*.*(..))")
    public void webLog(){}

    @Pointcut("@within(com.blackey.common.annotation.BlackeyHttpLog)")
    public void requestTypeLog() { }

    @Before("webLog()")
//    @Before("requestTypeLog()")
    public void doBefore(JoinPoint joinPoint){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = Objects.requireNonNull(attributes).getRequest();
        if(request.getRequestURL().equals("/sys/login")){
            return;
        }
        String paramStr;
        if (HttpMethod.POST.matches(request.getMethod())){
            Object[] paramsArray = joinPoint.getArgs();
            paramStr = argsArrayToString(paramsArray);
        }else {
            Map<String,String> returnMap = new HashMap<>();
            String getParams = request.getQueryString();
            paramStr = JSON.toJSONString(parseJson(returnMap,getParams));
        }
        String traceId= UUID.randomUUID().toString();
        StringBuilder sb = new StringBuilder();
        sb.append("\n\n")
                .append("TRACE_ID : ").append(traceId).append("\n")
                .append("REQ_HOST : ").append(request.getHeader("Host")).append("\n")
                .append("REQ_URI : ").append(request.getRequestURI()).append("\n")
                .append("HTTP_METHOD : ").append(request.getMethod()).append("\n")
                .append("CLASS_METHOD : ").append(joinPoint.getSignature().getDeclaringTypeName()).append(".").append(joinPoint.getSignature().getName()).append("\n")
                .append("REQ_ARGS : ").append(paramStr).append("\n");
        LOGGER.info(sb.toString());
        MDC.put(TRACE_ID,traceId);
        MDC.put(START_TIME,String.valueOf(System.currentTimeMillis()));
    }

    @AfterReturning(returning = "result", pointcut = "webLog()")
//    @AfterReturning(returning = "result", pointcut = "requestTypeLog()")
    public void doAfterReturning(Object result){
        String traceId = MDC.get(TRACE_ID);
        Long startTime = Long.valueOf(MDC.get(START_TIME));
        String resultJson = null;
        if(Objects.nonNull(result)){
            if(result instanceof Result){
                if(Objects.isNull(((Result) result).getData())){
                    resultJson = JSON.toJSONString(result);
                }else {
                    resultJson = DesensitizedUtils.getJson(result);
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append("\n\n")
                .append("TRACE_ID : ").append(traceId).append("\n")
                .append("EXECUTE_TIME : ").append(System.currentTimeMillis() - startTime).append("ms").append("\n")
                .append("RET_JSON : ").append(resultJson);

        LOGGER.info(sb.toString());
        MDC.clear();
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

    /**
     * POST 请求对象转json
     * @param paramsArray
     * @return
     */
    private String argsArrayToString(Object[] paramsArray){
        StringBuilder params = new StringBuilder();
        if (paramsArray !=null && paramsArray.length >0 ){
            Arrays.stream(paramsArray).filter(obj -> !(obj instanceof HttpServletRequest) &&
                    !(obj instanceof HttpServletResponse) && !(obj instanceof MultipartFile)
                    && !(obj instanceof BeanPropertyBindingResult))
                    .forEach(obj -> {
                        //Object jsonObj = JSON.toJSON(obj);
                        params.append(DesensitizedUtils.getJson(obj)).append("|");
                    });
        }
        return  params.toString();
    }

    /**
     * get 请求，将key=value&key=value转换为map
     * @param returnMap
     * @param getParams
     * @return
     */
    private Map<String,String> parseJson(Map<String,String> returnMap,String getParams){
        if(StringUtils.isBlank(getParams)){
            return null;
        }
        Arrays.stream(getParams.split("&"))
                .filter(kv -> kv.contains("="))
                .map(kv -> kv.split("="))
                .filter(array -> array.length > 1)
                .forEach(array -> returnMap.put(array[0],array[1]));
        return returnMap;
    }

}
