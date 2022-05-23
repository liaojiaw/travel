package com.xmairtravel.manager.api.config.web;

import com.alibaba.fastjson.JSONObject;
import com.xmairtravel.core.exception.ErrorEnums;
import com.xmairtravel.core.exception.IErrorCode;
import com.xmairtravel.core.server.wechat.entity.Constants;
import com.xmairtravel.core.server.wechat.entity.ResultBean;
import com.xmairtravel.core.utils.TokenUtil;
import io.jsonwebtoken.Claims;
import org.apache.http.util.TextUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Objects;

//登陆拦截器
@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        return this.verificationAdminToken(request, response);
    }

    private boolean verificationAdminToken(HttpServletRequest request, HttpServletResponse response)throws Exception{
        String token = request.getHeader(Constants.ACCESS_TOKEN);

        if(TextUtils.isEmpty(token)){
            makeResponse(ErrorEnums.NO_TOKEN,response);
            return false;
        }
        Claims tokenClaim = TokenUtil.getTokenClaim(token);
        if(Objects.isNull(tokenClaim)){
            makeResponse(ErrorEnums.INVALID_TOKEN,response);
            return false;
        }else{
            String subject = tokenClaim.getSubject();
            Map obj = JSONObject.parseObject(subject,Map.class);
            if(Objects.isNull(obj.get(Constants.OPPENID)) || StringUtils.isEmpty(Constants.OPPENID)){
                makeResponse(ErrorEnums.UNAUTHORIZED,response);
                return false;
            }
        }

        return true;
    }

    private static void makeResponse(IErrorCode errorCode, HttpServletResponse response)throws Exception{
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        ResultBean result = new ResultBean();
        result.setCode(errorCode.getCode());
        result.setMessage(errorCode.getMessage());
        response.getWriter().write(JSONObject.toJSONString(result));
    }

    public static void main(String[] args) {
        System.out.println(StandardCharsets.UTF_8.name());
    }
}

