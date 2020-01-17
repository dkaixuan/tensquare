package com.tensquare.recruit;

import com.tensquare.common.util.IdWorker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * @author kaixuan
 * @version 1.0
 * @date 2020/1/16 15:34
 */
@SpringBootApplication
public class RecruitApplication {
    public static void main(String[] args) {
        SpringApplication.run(RecruitApplication.class);
    }
    @Bean
    public IdWorker idWorker() {
        return new IdWorker(1, 1);
    }
}
