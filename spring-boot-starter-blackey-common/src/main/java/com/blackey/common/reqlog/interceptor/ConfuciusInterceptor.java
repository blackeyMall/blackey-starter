package com.blackey.common.reqlog.interceptor;

import com.blackey.common.mdc.MDCUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * @author : kai.sun
 * @version : 2019-01-05
 */

@Configuration
@ConfigurationProperties(prefix = "request")
public class ConfuciusInterceptor extends HandlerInterceptorAdapter {

    private static Logger log = LoggerFactory.getLogger(ConfuciusInterceptor.class);

    private long slowExecuteWarnTime = 1000;

    private boolean onlyOutputSlowTimeLog = false;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Class<?> type = handlerMethod.getBeanType();
            Method method = handlerMethod.getMethod();
            String t = type.getSimpleName();
            String m = method.getName();
            MDCUtils.addControllerMethod(t + "." + m);
        }
        MDCUtils.addExecuteStartTime(System.currentTimeMillis());
        return super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        long spentTime = System.currentTimeMillis() - MDCUtils.getExecuteStartTime();
        if (spentTime > slowExecuteWarnTime)
            log.warn(MDCUtils.MDC_INFO() + "\nEXECUTE_TOO_SLOW: It spent time " + spentTime + "ms! Is it a normal request?\n");
        else if (!onlyOutputSlowTimeLog) log.info(MDCUtils.MDC_INFO() + "\nEXECUTE_TIME " + spentTime + "ms\n");
        super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        MDCUtils.removeControllerMethod();
        MDCUtils.removeExecuteStartTime();
        super.afterCompletion(request, response, handler, ex);
    }

    public long getSlowExecuteWarnTime() {
        return slowExecuteWarnTime;
    }

    public void setSlowExecuteWarnTime(long slowExecuteWarnTime) {
        this.slowExecuteWarnTime = slowExecuteWarnTime;
    }

    public boolean isOnlyOutputSlowTimeLog() {
        return onlyOutputSlowTimeLog;
    }

    public void setOnlyOutputSlowTimeLog(boolean onlyOutputSlowTimeLog) {
        this.onlyOutputSlowTimeLog = onlyOutputSlowTimeLog;
    }
}
