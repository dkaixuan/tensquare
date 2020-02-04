package com.tensquare.friend.service;

import com.tensquare.friend.dao.FriendDao;
import com.tensquare.friend.dao.NoFriendDao;
import com.tensquare.friend.pojo.Friend;
import com.tensquare.friend.pojo.NoFriend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author kaixuan
 * @version 1.0
 * @date 2020/2/4 13:25
 */
@Service
@Transactional(rollbackFor =Exception.class)
public class FriendService {

    @Autowired
    private FriendDao friendDao;

    @Autowired
    private NoFriendDao noFriendDao;



    public int addFriend(String userid, String friendid) {
        //判断userid 到friend 是否有数据,有就是重复添加好友
        Friend friendFromDb = friendDao.findByUseridAndFriendid(userid, friendid);
        if (friendFromDb != null) {
            return 0;
        }else {

            //向喜欢表中添加记录
            Friend friend=new Friend();
            friend.setUserid(userid);
            friend.setFriendid(friendid);
            friend.setIslike("0");
            friendDao.save(friend);
        }
        //判断从Friendid到userid是否有数据，如果有，把双方的islike都置为1
        if (friendDao.findByUseridAndFriendid(userid, friendid) != null) {
            //把双方的islike都置为1
            friendDao.updateIslike("1", userid, friendid);
            friendDao.updateIslike("1", friendid, userid);
        }
        return 1;
    }


    /**
     * 不喜欢好友
     * @param userid
     * @param friendid
     * @return
     */
    public int addNoFriend(String userid, String friendid) {
        NoFriend noFriendFromDb = noFriendDao.findByUseridAndFriendid(userid, friendid);
        if (noFriendFromDb != null) {
            return 0;
        }else{
            NoFriend noFriend = new NoFriend();
            noFriend.setUserid(userid);
            noFriend.setFriendid(friendid);
            noFriendDao.save(noFriend);
            return 1;
        }

    }


    public void deleteFriend(String userid, String friendid) {
        //删除表中 userid到friendid 这条数据
        Friend friend = new Friend();
        friend.setUserid(userid);
        friend.setFriendid(friendid);
        friendDao.delete(friend);
        //更新friendid到userid的islike 为0
        friendDao.updateIslike("0", friendid, userid);


        //非好友表中添加数据
        NoFriend noFriend = new NoFriend();
        noFriend.setUserid(userid);
        noFriend.setFriendid(friendid);
        noFriendDao.save(noFriend);
    }
}
