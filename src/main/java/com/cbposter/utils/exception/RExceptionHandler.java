package com.cbposter.utils.exception;

import com.cbposter.utils.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @Auther: lc
 * @Date: 2020/1/8 11:22
 */
@ControllerAdvice
@ResponseStatus
public class RExceptionHandler {

    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * @methodName: handlerRException
     * @description: 捕捉自定义异常信息以Result结果返回
     */
    @ExceptionHandler(RException.class)
    @ResponseStatus
    @ResponseBody
    public Result handlerRException(RException e) {
        Result result = new Result();
        result.put("code", e.getCode());
        result.put("msg", e.getMsg());
        return result;
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseStatus
    @ResponseBody
    public Result handlerRException(MissingServletRequestParameterException e) {
        Result result = new Result();
        result.put("code", 500);
        result.put("msg", e.getMessage());
        return result;
    }


    @ExceptionHandler(Exception.class)
    @ResponseStatus
    @ResponseBody
    public Result handleException(Exception e) {
        logger.error(e.getMessage(), e);
        return Result.error();
    }
}

