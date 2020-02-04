package com.tensquare.friend.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

/**
 * @author kaixuan
 * @version 1.0
 * @date 2020/2/4 14:56
 */
@FeignClient("tensquare-user")
public interface UserClient {


    @PutMapping("/{userid}/{friendid}/{x}")
    public void updateFanscountAndFollowcount(@PathVariable String userid,
                                              @PathVariable String friendid,
                                              @PathVariable int x);

}
