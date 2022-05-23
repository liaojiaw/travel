package com.xmairtravel.core.exception;

import lombok.Data;

/**
 * 通用异常:当异常找不到细分项的时候，使用这个异常
 *
 * @author liuyiling
 * @date on 2018/12/24
 */
@Data
public class CommonException extends RuntimeException {

    public IErrorCode iErrorCode = ErrorEnums.SUCCESS;

    /**
     * 错误代码：eg.201
     */
    public Integer code = ErrorEnums.SUCCESS.getCode();

    /**
     * 错误消息：eg.第6个参数错误
     */
    public String message = ErrorEnums.SUCCESS.getMessage();;

    /**
     * 错误数据：eg.一般为null
     */
    public Object data;

    public CommonException(Object data) {
        this.data = data;
    }

    public CommonException(String message, Object data) {
        this.message = message;
        this.data = data;
    }

    public CommonException(IErrorCode iErrorCode, String message, Object data) {
        this.iErrorCode = iErrorCode;
        this.code = iErrorCode.getCode();
        this.message = iErrorCode.getMessage();
        this.message = message;
        this.data = data;
    }
}
