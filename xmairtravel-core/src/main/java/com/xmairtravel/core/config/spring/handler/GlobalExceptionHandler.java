package com.xmairtravel.core.config.spring.handler;

import com.xmairtravel.core.server.wechat.entity.Result;
import com.xmairtravel.core.exception.ApiInvokeException;
import com.xmairtravel.core.exception.CommonException;
import com.xmairtravel.core.exception.ErrorEnums;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author Liao
 * @version 1.0
 * @date 2021/8/23 8:31
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Controller 中校验参数报错统一走这个处理
     *
     * @param e MethodArgumentNotValidException
     * @return Result
     */
    @ExceptionHandler(ApiInvokeException.class)
    public Result<Object> apiInvokeExceptionHandler(ApiInvokeException e) {
        log.error("调用外部接口异常：{}", e.toString(), e);
        Result<Object> result = new Result();
        result.setCode(ErrorEnums.THIRD_PARTY_API_INVOKE_ERROR.getCode());
        result.setMsg(ErrorEnums.THIRD_PARTY_API_INVOKE_ERROR.getMessage());
        return result;
    }

    /**
     * Controller 中校验参数报错统一走这个处理
     *
     * @param e MethodArgumentNotValidException
     * @return Result
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result<Object> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        log.error("参数异常：{}", e.toString(), e);
        Result<Object> result = new Result();
        result.setCode(ErrorEnums.PARAM_ERROR.getCode());
        result.setMsg(e.getBindingResult().getFieldError().getDefaultMessage());
        return result;
    }

    /**
     * 没有命中异常处理的通通走这个
     *
     * @param e
     * @return
     */
    @ExceptionHandler(CommonException.class)
    public Result<Object> runtimeExceptionHandler(CommonException e) {
        logException(e);
        log.error("自定义异常：{}", e.toString(), e);

        Result<Object> result = new Result();
        result.setCode(ErrorEnums.BUSINESS_ERROR.getCode());
        result.setMsg(e.getMessage());
        return result;
    }

    /**
     * 没有命中异常处理的通通走这个
     *
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    public Result<Object> runtimeExceptionHandler(Exception e) {
        logException(e);
        log.error("系统未定义异常：{}", e.toString(), e);

        Result<Object> result = new Result();
        result.setCode(ErrorEnums.BUSINESS_ERROR.getCode());
        result.setMsg(ErrorEnums.BUSINESS_ERROR.getMessage());
        return result;
    }


    /** @ExceptionHandler
    可以指定处理特定异常(例如 ：@ExceptionHandler(value = BaseException.class))，
    这里捕获所有异常到一个方法中处理。

     @ResponseBody
     注解用于处理返回结果，不加上不会经过GlobalResponseHandler处理而是直接返回异常信息给页面，加上后返回结果
     会交给GlobalResponseHandler处理
     */

    private void logException(Throwable e){
        String message = e.getMessage();
        StackTraceElement[] stackTrace = e.getStackTrace();
        log.info("---------------------------------------------------");
        log.warn("Exception Msg : {}",message == null || message.isEmpty()?"NullPointException":message);
        for (StackTraceElement stackTraceElement : stackTrace) {
            log.error("\tat " + stackTraceElement);
        }
        log.info("---------------------------------------------------");
    }
}