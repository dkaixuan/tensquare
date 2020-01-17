package com.tensquare.common.exception;

import com.tensquare.common.entity.Result;
import com.tensquare.common.entity.StatusCode;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author kaixuan
 * @version 1.0
 * @date 2020/1/16 21:53
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result error() {
        return new Result(false, StatusCode.ERROR, "未知错误");
    }
}
