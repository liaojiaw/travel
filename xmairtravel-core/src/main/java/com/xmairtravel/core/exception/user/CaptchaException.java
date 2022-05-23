package com.xmairtravel.core.exception.user;

import com.xmairtravel.core.exception.BaseException;

/**
 * 验证码错误异常类
 * 
 * @author ruoyi
 */
public class CaptchaException extends UserException {
    private static final long serialVersionUID = 1L;

    public CaptchaException()
    {
        super("user.jcaptcha.error", null);
    }
}
