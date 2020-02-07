package com.tensquare.crawler.pipeline;

import com.tensquare.common.util.DownLoadUtil;
import com.tensquare.common.util.IdWorker;
import com.tensquare.crawler.dao.UserDao;
import com.tensquare.crawler.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.io.IOException;

/**
 * @author kaixuan
 * @version 1.0
 * @date 2020/2/7 16:29
 */
@Component
public class UserPipeline implements Pipeline {

    @Autowired
    private IdWorker idWorker;
    @Autowired
    private UserDao userDao;

    @Override
    public void process(ResultItems resultItems, Task task) {
        String nickname = resultItems.get("subNickname");
        String avatar = resultItems.get("avatar");
        String fileName = avatar.substring(avatar.lastIndexOf("/")+1);

        User user = new User();
        user.setId(idWorker.nextId()+"");
        user.setAvatar(avatar);
        user.setNickname(nickname);
        userDao.save(user);

        //下载图片
        try {
            DownLoadUtil.download(avatar,fileName,"e:/userimg");
        } catch (IOException e) {
            e.printStackTrace();
        }





    }
}
