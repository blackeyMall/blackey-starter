package com.blackey.common.reqlog.filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

/**
 * @author : kk
 * @version : 2019-01-03
 */
@Component
public class RegisterFilter {

    public static final String FILTER_ROOT_URI = "/*";

    @Bean
    public FilterRegistrationBean initConfuciusFilter() {
        FilterRegistrationBean registration = new FilterRegistrationBean<>(new ConfuciusFilter());
        registration.addUrlPatterns(FILTER_ROOT_URI);
        registration.setName(ConfuciusFilter.class.getSimpleName());
        registration.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return registration;
    }

}
