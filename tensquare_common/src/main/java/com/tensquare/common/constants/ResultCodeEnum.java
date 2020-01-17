package com.tensquare.common.constants;

import lombok.Getter;

/**
 * @author kaixuan
 * @version 1.0
 * @date 2020/1/13 21:45
 */
@Getter
public enum ResultCodeEnum {
    SUCCESS(20000, true, "成功"),
    UNKNOWN_REASON(20001,false,"未知错误");

    private Integer code;

    private Boolean flag;

    private String message;

    ResultCodeEnum(Integer code, boolean flag, String message) {
        this.code=code;
        this.flag=flag;
        this.message=message;
    }


}
