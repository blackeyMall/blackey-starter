package com.blackey.email.config;

import com.blackey.email.service.EmailService;
import com.blackey.email.service.impl.EmailServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 邮件服务
 * @author : kaven
 *
 * @date: 2018/12/17 13:49
 **/
@Configuration
public class EmailAutoConfiguration {

    @Bean
    public EmailService emailService(){
        return new EmailServiceImpl();
    }
}
