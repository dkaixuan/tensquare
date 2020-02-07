package com.tensquare.crawler.task;

import com.tensquare.crawler.pipeline.UserPipeline;
import com.tensquare.crawler.processor.UserProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.scheduler.RedisScheduler;

import java.nio.file.attribute.UserPrincipal;

/**
 * @author kaixuan
 * @version 1.0
 * @date 2020/2/7 16:42
 */
@Component
public class UserTask {

    @Autowired
    private UserProcessor userProcessor;

    @Autowired
    private RedisScheduler redisScheduler;

    @Autowired
    private UserPipeline userPipeline;

    /**
     * 爬取用户数据
     */
    @Scheduled(cron = "0 07 18 * * ?")
    public void userTask() {
        Spider spider = Spider.create(userProcessor)
                .setScheduler(redisScheduler)
                .addPipeline(userPipeline)
                .addUrl("https://blog.csdn.net/");
        spider.start();
    }
}
