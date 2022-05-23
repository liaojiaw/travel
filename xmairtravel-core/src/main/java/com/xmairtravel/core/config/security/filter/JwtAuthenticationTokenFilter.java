package com.xmairtravel.core.config.security.filter;

import com.alibaba.fastjson.JSONObject;
import com.xmairtravel.core.common.entity.LoginUser;
import com.xmairtravel.core.exception.ErrorEnums;
import com.xmairtravel.core.server.service.TokenService;
import com.xmairtravel.core.server.wechat.entity.Constants;
import com.xmairtravel.core.utils.SecurityUtil;
import com.xmairtravel.core.utils.TokenUtil;
import com.xmairtravel.core.utils.text.StringUtils;
import io.jsonwebtoken.Claims;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.Objects;

/**
 * token过滤器 验证token有效性
 *
 * @author ruoyi
 */

public class JwtAuthenticationTokenFilter extends OncePerRequestFilter
{
    private TokenService tokenService;
    public JwtAuthenticationTokenFilter(TokenService tokenService){
        this.tokenService = tokenService;
    }

    @Override
    @SneakyThrows
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) {
        String token = tokenService.getToken(request);
        if(!StringUtils.isEmpty(token)){
            UsernamePasswordAuthenticationToken authenticationToken = null;
            LoginUser loginUser = tokenService.getLoginUser(request);
            if (StringUtils.isNotNull(loginUser) && StringUtils.isNull(SecurityUtil.getAuthentication())) {
                tokenService.verifyToken(loginUser);
                authenticationToken = new UsernamePasswordAuthenticationToken(loginUser,
                        null, loginUser.getAuthorities());
            }else{
                Claims tokenClaim = tokenService.parseToken(token);
                if(!Objects.isNull(tokenClaim.get(Constants.OPPENID))){
                    authenticationToken = new UsernamePasswordAuthenticationToken(tokenClaim,
                            null, null);
                }
            }

            if(Objects.nonNull(authenticationToken)){
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }
        chain.doFilter(request, response);
    }


}
