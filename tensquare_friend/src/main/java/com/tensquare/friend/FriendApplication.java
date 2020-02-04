package com.tensquare.friend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author kaixuan
 * @version 1.0
 * @date 2020/2/4 12:59
 */
@EnableEurekaClient
@EnableFeignClients
@SpringBootApplication
public class FriendApplication {
    public static void main(String[] args) {
        SpringApplication.run(FriendApplication.class, args);
    }

}
