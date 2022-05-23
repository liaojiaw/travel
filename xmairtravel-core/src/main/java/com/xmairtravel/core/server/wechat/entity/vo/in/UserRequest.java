package com.xmairtravel.core.server.wechat.entity.vo.in;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@ApiModel(description = "接驳车入参")
public class UserRequest {

    @ApiModelProperty(value = "主键 新增时传空", example = "1")
    private Integer userId;
    /**
     * 用户姓名
     */
    @NotNull
    @ApiModelProperty(value = "用户姓名", example = "张三" ,required = true)
    private String userName;
    /**
     * 员工工号
     */
    @ApiModelProperty(value = "员工工号", example = "94999" )
    private String staffCode;
    /**
     * 手机号码
     */
    @NotNull
    @ApiModelProperty(value = "手机号码", example = "12345678901" ,required = true)
    private String mobile;
    /**
     * openid
     */
    @ApiModelProperty(value = "openid", example = "12242fdr3qfd23")
    private String openid;
    /**
     * 用户状态
     */
    @ApiModelProperty(value = "0 删除 1 正常 2 冻结", example = "1" ,required = true)
    private Integer status;
    /**
     * 用户身份ID
     */
    @ApiModelProperty(value = "用户身份ID", example = "1" ,required = true)
    private Integer identityId;

}
