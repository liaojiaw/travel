package com.xmairtravel.core.server.wechat.entity.vo.out;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class WxLoginResponse {
    /**
     * openid
     */
    @ApiModelProperty(value = "openid", example = "1")
    private String openid;
    /**
     * token
     */
    @ApiModelProperty(value = "token", example = "1")
    private String token;
    /**
     * openid
     */
    @ApiModelProperty(value = "身份", example = "custom")
    private String identity;
}
