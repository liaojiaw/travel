package com.xmairtravel.core.server.wechat.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.xmairtravel.core.common.entity.LoginUser;
import com.xmairtravel.core.exception.ServiceException;
import com.xmairtravel.core.exception.user.UserPasswordNotMatchException;
import com.xmairtravel.core.manager.AsyncManager;
import com.xmairtravel.core.manager.factory.AsyncFactory;
import com.xmairtravel.core.server.manager.mapper.SysPostMapper;
import com.xmairtravel.core.server.manager.mapper.SysUserMapper;
import com.xmairtravel.core.server.service.TokenService;
import com.xmairtravel.core.server.wechat.dao.master.ReWechatUserinfoDao;
import com.xmairtravel.core.server.wechat.entity.Constants;
import com.xmairtravel.core.server.wechat.entity.Result;
import com.xmairtravel.core.server.wechat.entity.bo.ReWechatUserinfoBO;
import com.xmairtravel.core.server.wechat.entity.common.RequestUriConst;
import com.xmairtravel.core.server.wechat.entity.vo.out.WxLoginResponse;
import com.xmairtravel.core.server.wechat.service.IWechatService;
import com.xmairtravel.core.utils.AesCbcUtil;
import com.xmairtravel.core.utils.HttpClientUtils;
import com.xmairtravel.core.utils.MessageUtils;
import com.xmairtravel.core.utils.TokenUtil;
import com.xmairtravel.core.utils.text.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;
import java.util.Objects;

@Slf4j
@Service
public class WechatServiceImpl implements IWechatService {
    @Value("${wx.appid:null}")
    private String appid;
    @Value("${wx.appsecret:null}")
    private String appsecret;

    @Autowired
    TokenService tokenService;

    @Autowired
    ReWechatUserinfoDao wechatUserinfoDao;

    @Autowired
    SysUserMapper userMapper;

    @Resource
    private AuthenticationManager authenticationManager;

    @Override
    public Result<String> wxOauth(String encryptedData, String iv, String code) {
            String requestUrl = RequestUriConst.WX_GETOPENID
                    .replace("APPID", appid).replace("SECRET", appsecret).replace("CODE", code);
            String result = HttpClientUtils.syncGet(requestUrl, null, null, String.class);
            JSONObject jsonObject = JSON.parseObject(result);
            //获取openid 以及密钥session_key
            String openid = jsonObject.getString("openid");
            String session_key = jsonObject.get("session_key").toString();
            //2.使用私钥值 和 算法向量值 加密的数据进行解密
            JSONObject userInfo = AesCbcUtil.getWechetEncryptedData(encryptedData, session_key, iv);
            //查询用户是否已经授权登陆过小程序
            ReWechatUserinfoBO reWechatUserinfoBO = new ReWechatUserinfoBO();
            reWechatUserinfoBO.setOpenid(openid);
            ReWechatUserinfoBO reWechatUserinfoDB = wechatUserinfoDao.selectOne(reWechatUserinfoBO);
            if(Objects.isNull(reWechatUserinfoDB)){
                //没有授权登陆过则新增一条用户数据
                reWechatUserinfoBO.setNickname(userInfo.getString("nickName"));
                reWechatUserinfoBO.setAvatarUrl(userInfo.getString("avatarUrl"));
                reWechatUserinfoBO.setUnionId(userInfo.getString("unionId"));
                reWechatUserinfoBO.setCreateTime(System.currentTimeMillis());
                reWechatUserinfoBO.setUpdateTime(System.currentTimeMillis());
                wechatUserinfoDao.insertSelective(reWechatUserinfoBO);
            }else{
                reWechatUserinfoDB.setUpdateTime(System.currentTimeMillis());
                wechatUserinfoDao.updateByPrimaryKeySelective(reWechatUserinfoDB);
            }
            return Result.success(openid);
    }

    @Override
    public Result<JSONObject> token() {
            String requestUrl = RequestUriConst.WX_TOKEN
                    .replace("APPID", appid).replace("SECRET", appsecret);
            JSONObject result = HttpClientUtils.syncGet(requestUrl, null, null, JSONObject.class);
            return Result.success("获取token成功",result);
    }

    @Override
    public Result<WxLoginResponse> wxLogin(String code) {
        try {
            String requestUrl = RequestUriConst.WX_LOGIN
                    .replace("APPID", appid).replace("SECRET", appsecret).replace("CODE", code);
            String result = HttpClientUtils.syncGet(requestUrl, null, null, String.class);
            JSONObject jsonObject = JSON.parseObject(result);
            String openid = jsonObject.getString("openid");
            String sessionKey = jsonObject.getString("session_key");
            if(StringUtils.isEmpty(openid)){
                return Result.error("code无效");
            }
            Map<String,Object> maps = Maps.newHashMap();
            maps.put("openid",openid);
            maps.put("sessionKey",sessionKey);
            String token = tokenService.createToken(maps);
            WxLoginResponse wxLoginResponse = new WxLoginResponse();
            wxLoginResponse.setOpenid(openid);
            wxLoginResponse.setIdentity("custom");
            wxLoginResponse.setToken(token);
            return Result.success("登陆成功",wxLoginResponse);
        } catch (Exception e) {
            return Result.error("登陆失败");
        }
    }

    @Override
    public Result<WxLoginResponse> wxBusinessLogin(String code, String phoneNum) {
        Result<WxLoginResponse> wxLoginResponseResult = wxLogin(code);
        String postCode = userMapper.selectPostCodeByPhone(phoneNum);
        wxLoginResponseResult.getData().setIdentity(postCode);
        return wxLoginResponseResult;
    }
}