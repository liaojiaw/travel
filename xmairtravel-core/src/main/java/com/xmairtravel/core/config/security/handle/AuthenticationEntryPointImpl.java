package com.xmairtravel.core.config.security.handle;

import com.alibaba.fastjson.JSON;
import com.xmairtravel.core.exception.ErrorEnums;
import com.xmairtravel.core.server.wechat.entity.Result;
import com.xmairtravel.core.server.wechat.entity.ResultBean;
import com.xmairtravel.core.utils.ServletUtil;
import com.xmairtravel.core.utils.text.StringUtils;
import io.swagger.models.auth.In;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;

/**
 * 认证失败处理类 返回未授权
 *
 * @author ruoyi
 */
@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint, Serializable
{
    private static final long serialVersionUID = -8970718410437077606L;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e)
            throws IOException
    {
        Integer code = ErrorEnums.UNAUTHORIZED.getCode();
        String msg = StringUtils.format("请求访问：{}，认证失败，无法访问系统资源", request.getRequestURI());
        ServletUtil.renderString(response, JSON.toJSONString(Result.error(code, msg)));
    }
}
