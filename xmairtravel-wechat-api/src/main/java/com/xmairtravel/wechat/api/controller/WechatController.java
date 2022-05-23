package com.xmairtravel.wechat.api.controller;

import com.alibaba.fastjson.JSONObject;
import com.xmairtravel.core.server.wechat.entity.Result;
import com.xmairtravel.core.server.wechat.entity.vo.out.WxLoginResponse;
import com.xmairtravel.core.server.wechat.service.IWechatService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = "管理类-微信小程序",description = "微信小程序")
@RequestMapping(value = "/wx")
public class WechatController {

    @Autowired
    IWechatService wechatService;

    @GetMapping(value="/oauth")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "encryptedData", required = true, value = "密文", dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "iv", required = true, value = "偏移量", dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "code", required = true, value = "code", dataType = "String")
    })
    @ApiOperation(value = "微信授权(获取用户信息)", httpMethod = "GET", notes = "微信授权(获取用户信息)", produces = "application/json")
    public Result<String> wxOauth(String encryptedData, String iv, String code){
      return wechatService.wxOauth(encryptedData,iv,code);
    }

    @GetMapping(value="/login")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "code", required = true, value = "code", dataType = "String")
    })
    @ApiOperation(value = "微信登陆", httpMethod = "GET", notes = "微信登陆", produces = "application/json")
    public Result<WxLoginResponse> wxLogin(String code){
        return wechatService.wxLogin(code);
    }

    @GetMapping(value="/business/login")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "code", required = true, value = "code", dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "phoneNum", required = true, value = "phoneNum", dataType = "String")
    })
    @ApiOperation(value = "微信登陆(员工)", httpMethod = "GET", notes = "微信登陆(员工)", produces = "application/json")
    public Result<WxLoginResponse> wxBusinessLogin(String code,String phoneNum){
        return wechatService.wxBusinessLogin(code,phoneNum);
    }

    @GetMapping(value="/token")
    @Cacheable(value = "wxTokenCache")
    @ApiOperation(value = "获取token", httpMethod = "GET", notes = "获取token", produces = "application/json")
    public Result<JSONObject> wxtoken(){
        return wechatService.token();
    }



}
