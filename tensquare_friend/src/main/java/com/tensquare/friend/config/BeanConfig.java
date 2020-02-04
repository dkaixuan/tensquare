package com.tensquare.friend.config;

import com.tensquare.common.util.IdWorker;
import com.tensquare.common.util.JwtUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author kaixuan
 * @version 1.0
 * @date 2020/1/29 12:20
 */
@Configuration
public class BeanConfig {

    @Bean
    public IdWorker idWorkker(){
        return new IdWorker(1, 1);
    }


    @Bean
    public JwtUtil jwtUtil(){
        return new JwtUtil();
    }


    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
