package com.tensquare.qa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author kaixuan
 * @version 1.0
 * @date 2020/1/16 21:58
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class QaApplication {
    public static void main(String[] args) {
        SpringApplication.run(QaApplication.class);
    }
}
