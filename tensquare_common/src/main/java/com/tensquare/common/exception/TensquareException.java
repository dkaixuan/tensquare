package com.tensquare.common.exception;

import com.tensquare.common.constants.ResultCodeEnum;
import lombok.Data;

/**
 * @author kaixuan
 * @version 1.0
 * @date 2020/1/23 20:05
 * 自定义异常
 */
@Data
public class TensquareException extends RuntimeException{

    /**
     * 状态码
     */
    private Integer code;

    public TensquareException(Integer code, String message) {
        super(message);
        this.code=code;
    }

    /**
     * 接受枚举类型
     * @param resultCodeEnum
     */
    public TensquareException(ResultCodeEnum resultCodeEnum) {
        super(resultCodeEnum.getMessage());
        this.code = resultCodeEnum.getCode();
    }









}
