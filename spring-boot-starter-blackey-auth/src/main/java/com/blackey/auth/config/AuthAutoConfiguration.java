package com.blackey.auth.config;

import com.blackey.auth.service.AuthService;
import com.blackey.auth.service.impl.AuthServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * auto configuration
 * @author wangwei : kaven
 *
 * @date: 2019/1/7 15:13
 **/
@Configuration
public class AuthAutoConfiguration {

    @Bean
    public AuthService authService(){
        return new AuthServiceImpl();
    }
}
