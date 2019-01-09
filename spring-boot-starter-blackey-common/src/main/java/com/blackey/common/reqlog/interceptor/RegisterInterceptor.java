package com.blackey.common.reqlog.interceptor;

import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

/**
 * @author : kai.sun
 * @version : 2019-01-05
 * @description :
 **/

@Component
public class RegisterInterceptor implements WebMvcConfigurer {

    public static final String INTERCEPTOR_ROOT_URI = "/**";

    @Resource
    private ConfuciusInterceptor confuciusInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(confuciusInterceptor)
                .addPathPatterns(INTERCEPTOR_ROOT_URI)
                .order(Ordered.HIGHEST_PRECEDENCE);
    }

}
