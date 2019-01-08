package com.blackey.common.runtime;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Configuration
public class RuntimeApplication {

    public RuntimeApplication() throws UnknownHostException {
        applicationIp = InetAddress.getLocalHost();
    }

    @Value("${spring.application.name:UNKNOWN_APP}")
    public void setApplicationName(String name) {
        RuntimeApplication.applicationName = name;
    }

    public static String applicationName;

    public static InetAddress applicationIp;

}
