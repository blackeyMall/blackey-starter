package com.blackey.common.config;

import com.blackey.common.aop.RequestLogAop;
import com.blackey.common.aop.ValidateParamAop;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * AOP 自动配置类
 * Created by Kaven
 * Date: 2018/5/21
 */
@Configuration
public class AopAutoConfiguration {

    private static final Logger LOGGER = LoggerFactory.getLogger(AopAutoConfiguration.class);

    @Bean
    @ConditionalOnProperty(value = {"spring.validaop.enabled"},matchIfMissing = true)
    public ValidateParamAop getValidateParamAop(){
        LOGGER.info("----init ValidateParamAop-------");
        return new ValidateParamAop();
    }

//    @Bean
//    @ConditionalOnProperty(value = {"spring.logaop.enabled"},matchIfMissing = true)
//    public RequestLogAop getRequestLogAop(){
//        LOGGER.info("-----------init RequestLogAop------");
//        return new RequestLogAop();
//    }
}
