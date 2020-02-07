package com.tensquare.crawler.processor;

import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;

/**
 * @author kaixuan
 * @version 1.0
 * @date 2020/2/7 13:47
 * 文章爬取类
 */
@Component
public class ArticleProcessor implements PageProcessor {
    @Override
    public void process(Page page) {

        page.addTargetRequests(page.getHtml().regex("https://blog.csdn.net/[a-z 0-9 -]+/article/details/[0-9]{9}").all());
        //文章标题
        String title = page.getHtml().xpath("//*[@id=\"mainBox\"]/main/div[1]/div/div/div[1]/h1").get();
        //文章内容
        String content = page.getHtml().xpath("//*[@id=\"content_views\"]").get();

        String subTitle = title.substring(title.indexOf(">")+1,title.lastIndexOf("<"));

        if (title != null&&content!=null) {
            page.putField("title", subTitle);
            page.putField("content", content);
        }else {
            //跳过
            page.setSkip(true);
        }

    }

    @Override
    public Site getSite() {
        return Site.me().setSleepTime(100).setRetryTimes(3);
    }



}
