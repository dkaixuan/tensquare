package com.tensquare.user.config;

import com.tensquare.user.interceptors.AuthInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.net.Authenticator;

/**
 * @author kaixuan
 * @version 1.0
 * @date 2020/1/29 17:31
 */
@Configuration
public class WebMvcConfiguration extends WebMvcConfigurationSupport {

    @Autowired
    private AuthInterceptor authInterceptor;


    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authInterceptor)
        .addPathPatterns("/**")
        .excludePathPatterns("/**/login/**");
    }
}
