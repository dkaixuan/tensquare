package com.tensquare.qa.client;

import com.tensquare.common.entity.Result;
import com.tensquare.common.entity.StatusCode;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author kaixuan
 * @version 1.0
 * @date 2020/2/1 19:51
 */
@FeignClient("tensquare-base")
public interface BaseClient {



    @GetMapping("/label/{labelId}")
    public Result findById(@PathVariable("labelId") String labelId);
}
