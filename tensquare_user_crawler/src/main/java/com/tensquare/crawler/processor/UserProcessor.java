package com.tensquare.crawler.processor;

import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

/**
 * @author kaixuan
 * @version 1.0
 * @date 2020/2/7 16:09
 */
@Component
public class UserProcessor implements PageProcessor {
    @Override
    public void process(Page page) {
        page.addTargetRequests(page.getHtml().links().regex("https://blog.csdn.net/[a-z 0-9 -]+/article/details/[0-9]{9}").all());
        String nickname = page.getHtml().xpath("//*[@id=\"uid\"]").get();
        String subNickname = nickname.substring(nickname.indexOf(">")+1, nickname.lastIndexOf("<"));
        String avatar= page.getHtml().xpath("//*[@id=\"asideProfile\"]/div[1]/div[1]/a/").css("img","src").get();

        if (subNickname != null&&avatar!=null) {
            page.putField("subNickname", subNickname);
            page.putField("avatar", avatar);
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
