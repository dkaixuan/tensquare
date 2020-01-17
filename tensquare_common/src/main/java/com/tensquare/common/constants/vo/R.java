package com.tensquare.common.constants.vo;

import com.tensquare.common.constants.ResultCodeEnum;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @author kaixuan
 * @version 1.0
 * @date 2020/1/13 21:28
 * 全局统一返回结果
 */
@Data
public class R {

    private Integer code;

    private Boolean flag;

    private String message;


    private Map<String, Object> data = new HashMap<>();


    public R() {
    }


    public static R ok() {
        R r = new R();
        r.setCode(ResultCodeEnum.SUCCESS.getCode());
        r.setFlag(ResultCodeEnum.SUCCESS.getFlag());
        r.setMessage(ResultCodeEnum.SUCCESS.getMessage());
        return r;
    }

    public static R error() {
        R r = new R();
        r.setCode(ResultCodeEnum.UNKNOWN_REASON.getCode());
        r.setFlag(ResultCodeEnum.UNKNOWN_REASON.getFlag());
        r.setMessage(ResultCodeEnum.UNKNOWN_REASON.getMessage());
        return r;
    }

    public R message(String message) {
        this.setMessage(message);
        return this;
    }

    public R code(Integer code) {
        this.setCode(code);
        return this;
    }


    public R datas(String key, Object value) {
        this.data.put(key, value);
        return this;
    }

    public R datas(Map<String, Object> map) {
        this.setData(map);
        return this;
    }







}
