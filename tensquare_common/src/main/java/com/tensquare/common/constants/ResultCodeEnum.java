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
    MESSAGE_RANDOM_CODE_LENGTH_INVALID(20000, true, "验证码长度不合法"),
    SMS_CODE_INVALID(20001, false, "请输入正确的验证码"),
    SMS_CODE_INTERFACE_ERROR(20001, false, "短信接口异常"),
    FORWARD_ERROR(20001, false, "转发一场"),
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
