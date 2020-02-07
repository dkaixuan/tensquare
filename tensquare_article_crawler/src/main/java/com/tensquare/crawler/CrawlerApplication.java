package com.tensquare.crawler;

import com.tensquare.common.util.IdWorker;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import us.codecraft.webmagic.scheduler.RedisScheduler;

/**
 * @author kaixuan
 * @version 1.0
 * @date 2020/1/13 19:25
 */

@EnableScheduling
@SpringBootApplication
public class CrawlerApplication {

    @Value("${redis.host}")
    private String redisHost;


    public static void main(String[] args) {
        SpringApplication.run(CrawlerApplication.class);
    }



    @Bean
    public IdWorker idWorker() {
        return new IdWorker(1, 1);
    }

    @Bean
    public RedisScheduler redisScheduler(){
        return new RedisScheduler(redisHost);
    }

}
