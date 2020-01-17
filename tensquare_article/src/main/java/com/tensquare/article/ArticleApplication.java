package com.tensquare.article;

import com.tensquare.common.util.IdWorker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


/**
 * @author kaixuan
 * @version 1.0
 * @date 2020/1/17 16:37
 */
@SpringBootApplication
public class ArticleApplication {
    public static void main(String[] args) {
        SpringApplication.run(ArticleApplication.class);
    }


    @Bean
    public IdWorker idWorker() {
        return new IdWorker(1,1);
    }


}
