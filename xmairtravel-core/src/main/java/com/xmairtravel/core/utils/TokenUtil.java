package com.xmairtravel.core.utils;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.xmairtravel.core.server.wechat.entity.Constants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;
import java.util.Objects;

@Component
public class TokenUtil {

    private static String secret = "iwqjhda8232bjgh432";
    @Value("${token.secret:secret}")
    public void setSecret(String secret_) {
        secret = secret_;
    }

    private static long expire = 3000;
    @Value("${token.expireTime:3000}")
    public void setExpire(Long expire_) {
        expire = expire_;
    }
    /*
     * 根据身份ID标识，生成Token
     */
    public static String getToken (Map<String,Object> maps){
        Date nowDate = new Date();
        //过期时间
        Date expireDate = new Date(nowDate.getTime() + expire * 1000);
        return Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setSubject(JSONObject.toJSONString(maps))
                .setIssuedAt(nowDate)
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }


    /*
     * 获取 Token 中注册信息
     */
    public static Claims getTokenClaim (String token) {
        try {
            return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }


    /*
     * 获取 Token 中openid
     */
    public static String getOpenId () {
        try {
            HttpServletRequest request = ServletUtil.getRequest();
            String token = request.getHeader(Constants.ACCESS_TOKEN);
            Claims tokenClaim = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
            String subject = tokenClaim.getSubject();
            Map obj = JSONObject.parseObject(subject,Map.class);
            return Objects.isNull(obj.get(Constants.OPPENID))?"":obj.get(Constants.OPPENID).toString();
        }catch (Exception e){
            return null;
        }
    }


    public static void main(String[] args) {
        Map<String,Object> maps = Maps.newHashMap();
        maps.put("openid","openid");
        maps.put("sessionKey","sessionKey");
        String token = getToken(maps);
        System.out.println(token);
        Claims tokenClaim = getTokenClaim(token);
        System.out.println(tokenClaim);

    }
}
