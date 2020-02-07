package com.tensquare.crawler.pipeline;

import com.tensquare.common.util.IdWorker;
import com.tensquare.crawler.dao.ArticleDao;
import com.tensquare.crawler.pojo.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

/**
 * @author kaixuan
 * @version 1.0
 * @date 2020/2/7 14:19
 * 文章入库类
 */
@Component
public class ArticleDbPipeline implements Pipeline {

    @Autowired
    private ArticleDao articleDao;

    @Autowired
    private IdWorker idWorker;



    private String channelId;
    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    @Override
    public void process(ResultItems resultItems, Task task) {
        //取出文章标题
        String title = resultItems.get("title");
        //取出文章内容
        String content = resultItems.get("content");

        Article article = new Article();
        article.setId(idWorker.nextId()+"");
        article.setChannelid(channelId);
        article.setTitle(title);
        article.setContent(content);

        articleDao.save(article);
    }
}
