package com.xmairtravel.core.server.wechat.entity.bo;

import lombok.Data;

@Data
public class WechatUserBO {
    private Integer id;
    //token
    private String token;
    //用户昵称
    private String nickname;
    //用户头像
    private String avatarUrl;
    //性别  0-未知、1-男性、2-女性
    private Integer gender;
    //国家
    private String country;
    //省份
    private String province;
    //城市
    private String city;
    //手机号码
    private String mobile;
    //小程序openId
    private String openid;
    //小程序unionId
    private String unionId;
    //创建时间
    private Long createTime;
    //更新时间
    private Long updateTime;
}