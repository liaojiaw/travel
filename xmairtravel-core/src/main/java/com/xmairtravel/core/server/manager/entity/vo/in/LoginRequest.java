package com.xmairtravel.core.server.manager.entity.vo.in;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 用户登录对象
 * 
 * @author ruoyi
 */
@Data
public class LoginRequest
{
    /**
     * 用户名
     */
    @ApiModelProperty(value = "用户名（工号）",required = true,example = "95923")
    private String username;

    /**
     * 用户密码
     */
    @ApiModelProperty(value = "用户密码",required = true, example = "123456")
    private String password;

    /**
     * 验证码
     */
    @ApiModelProperty(value = "验证码",required = true, example = "123456")
    private String code;

    /**
     * 唯一标识
     */
    @ApiModelProperty(value = "唯一标识",example = "123456")
    private String uuid;

}
