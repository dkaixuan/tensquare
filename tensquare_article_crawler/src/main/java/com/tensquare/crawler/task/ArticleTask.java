package com.tensquare.crawler.task;

import com.tensquare.crawler.pipeline.ArticleDbPipeline;
import com.tensquare.crawler.processor.ArticleProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.scheduler.RedisScheduler;


/**
 * @author kaixuan
 * @version 1.0
 * @date 2020/2/7 14:24
 * 文章人物类
 */
@Component
public class ArticleTask {

    @Autowired
    private ArticleProcessor articleProcessor;

    @Autowired
    private ArticleDbPipeline articleDbPipeline;

    @Autowired
    private RedisScheduler redisScheduler;


    /**
     * 爬取java文章
     */

    @Scheduled(cron ="0 35 15 * * ?")
    public void aiTask() {
        articleDbPipeline.setChannelId("java");
        Spider spider = Spider
                .create(articleProcessor)
                .addUrl("https://blog.csdn.net/nav/java")
                .addPipeline(articleDbPipeline)
                .setScheduler(redisScheduler);
        //多线程爬取
        spider.start();
    }



}
