package com.xmairtravel.core.server.wechat.entity.bo;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * (ReWechatUserinfo)实体类
 *
 * @author makejava
 * @since 2022-04-24 16:07:23
 */
@Data
@Table(name = "re_wechat_userinfo")
public class ReWechatUserinfoBO implements Serializable {
    private static final long serialVersionUID = -68266340428020721L;
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userInfoId;
    /**
     * 小程序唯一标识
     */
    private String openid;
    /**
     * 昵称
     */
    private String nickname;
    /**
     * 用户头像地址
     */
    private String avatarUrl;
    /**
     * 性别  0-未知、1-男性、2-女性
     */
    private Integer gender;
    /**
     * 国家
     */
    private String country;
    /**
     * 省份
     */
    private String province;
    /**
     * 城市
     */
    private String city;
    /**
     * 手机号码
     */
    private String mobile;
    /**
     * 小程序unionId
     */
    private String unionId;
    /**
     * 创建时间
     */
    private Long createTime;
    /**
     * 更新时间
     */
    private Long updateTime;

}

