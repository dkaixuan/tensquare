package com.tensquare.user.dao;

import com.tensquare.user.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * 数据访问接口
 * @author Administrator
 *
 */
public interface UserDao extends JpaRepository<User,String>,JpaSpecificationExecutor<User>{

    /**
     * 更新关注数
     * @param userid
     * @param x
     */
    @Modifying
    @Query("update User u set u.followcount=u.followcount+?2 where u.id=?1")
    public void incFollowcount(String userid, int x);

    /**
     * 更新粉丝数
     * @param userid
     * @param x
     */
    @Modifying
    @Query("update User u set u.fanscount=u.fanscount+?2 where u.id=?1")
    public void incFanscount(String userid, int x);

    //查询用户
    public User findByMobile(String mobile);


    /**
     * 更新好友粉丝数
     * @param x
     * @param friendid
     */
    @Modifying
    @Query(value ="update tb_user set fanscount=fanscount+? where id=?",nativeQuery =true)
    void updateFriendFanscount(int x, String friendid);

    @Query(value ="update tb_user set followcount=followcount+? where id=?",nativeQuery = true)
    void updateUserFollowcount(int x, String userid);
}
