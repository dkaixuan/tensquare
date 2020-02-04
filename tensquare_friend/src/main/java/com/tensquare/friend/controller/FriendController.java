package com.tensquare.friend.controller;

import com.tensquare.common.entity.Result;
import com.tensquare.common.entity.StatusCode;
import com.tensquare.friend.client.UserClient;
import com.tensquare.friend.service.FriendService;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author kaixuan
 * @version 1.0
 * @date 2020/2/4 13:08
 */
@CrossOrigin
@RestController
@RequestMapping("/friend")
public class FriendController {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private FriendService friendService;

    @Autowired
    private UserClient userClient;


    @PutMapping("/like/{friendid}/{type}")
    public Result addFriendOrNonFriend(@PathVariable String friendid,
                                       @PathVariable String type) {
        //验证是否登录,并且拿到当前登录的用户id
        Claims claims = (Claims) request.getAttribute("user_claims");
        if(claims==null){
            return new Result(false, StatusCode.ACCESSERROR,"无权访问");
        }
        if (type != null) {
            String userid = claims.getId();
            if (type.equals("1")) {
                int flag = friendService.addFriend(userid, friendid);
                if (flag==0) {
                    return new Result(true, StatusCode.REPERROR, "已经添加过此好友");
                }
                if (flag==1){
                    //调用用户微服务 更新好友粉丝数和用户关注数
                    userClient.updateFanscountAndFollowcount(userid,friendid,1);
                    return new Result(true, StatusCode.REPERROR, "添加好友成功");
                }
                //不喜欢  添加非好友
            } else if (type.equals("2")){
                int flag = friendService.addNoFriend(userid, friendid);
                if (flag ==0) {
                    return new Result(true, StatusCode.REPERROR, "已经添加过非好友了");
                }
                if (flag == 1) {
                    return new Result(true, StatusCode.REPERROR, "添加非好友成功");
                }

            }
        }else {
            return new Result(true, StatusCode.ERROR, "参数异常");
        }
       return new Result(true, StatusCode.OK, "操作成功");
    }

    @DeleteMapping("/{friendid}")
    public Result deleteFriend(@PathVariable String friendid) {
        //验证是否登录,并且拿到当前登录的用户id
        Claims claims = (Claims) request.getAttribute("user_claims");
        if(claims==null){
            return new Result(false, StatusCode.ACCESSERROR,"无权访问");
        }
        String userid = claims.getId();
        friendService.deleteFriend(userid, friendid);
        userClient.updateFanscountAndFollowcount(userid,friendid,-1);
        return new Result(true, StatusCode.OK,"删除成功");
    }









}
