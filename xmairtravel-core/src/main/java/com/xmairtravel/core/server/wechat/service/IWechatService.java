package com.xmairtravel.core.server.wechat.service;

import com.alibaba.fastjson.JSONObject;
import com.xmairtravel.core.server.wechat.entity.Result;
import com.xmairtravel.core.server.wechat.entity.vo.out.WxLoginResponse;

public interface IWechatService {

    Result<String> wxOauth(String encryptedData, String iv, String code);

    Result<JSONObject> token();

    Result<WxLoginResponse> wxLogin(String code);

    Result<WxLoginResponse> wxBusinessLogin(String code, String phoneNum);
}
