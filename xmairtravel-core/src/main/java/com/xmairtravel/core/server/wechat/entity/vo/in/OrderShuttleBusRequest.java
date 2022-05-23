package com.xmairtravel.core.server.wechat.entity.vo.in;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.models.auth.In;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@ApiModel(description = "预定接驳车入参")
public class OrderShuttleBusRequest {
    @NotNull(message = "openid不能为空")
    @ApiModelProperty(value = "openid")
    private String openid;
    /**
     * 用户姓名
     */
    @NotNull
    @ApiModelProperty(value = "用户姓名", example = "张三" ,required = true)
    private String userName;
    /**
     * 用户姓名
     */
    @NotNull
    @ApiModelProperty(value = "用户性别用户性别（0男 1女 2未知）", example = "0" ,required = true)
    private Integer sex;
    /**
     * 手机号码
     */
    @NotNull
    @ApiModelProperty(value = "手机号码", example = "12345678901" ,required = true)
    private String mobile;
    /**
     * 预定开始时间
     */
    @NotNull
    @ApiModelProperty(value = "预定开始时间", example = "12345678901" ,required = true)
    private Long reserveStartTime;
    /**
     * 预定结束时间
     */
    @ApiModelProperty(value = "预定结束时间", example = "12345678901")
    private Long reserveEndTime;
    /**
     * 出发地
     */
    @NotNull
    @ApiModelProperty(value = "出发地", example = "黄花机场" ,required = true)
    private String departure;
    /**
     * 目的地
     */
    @NotNull
    @ApiModelProperty(value = "目的地", example = "湖南分公司" ,required = true)
    private String destination;
    /**
     * 出发地坐标
     */
    @NotNull
    @ApiModelProperty(value = "出发地坐标", example = "3.1415926" ,required = true)
    private String departureCoordinate;
    /**
     * 目的地坐标
     */
    @NotNull
    @ApiModelProperty(value = "目的地坐标", example = "3.1415926" ,required = true)
    private String destinationCoordinate;
    /**
     * 航班号
     */
    @ApiModelProperty(value = "航班号", example = "ABC123" ,required = true)
    private String flight;

    /**
     * 是否使用白鹭厅
     */
    @ApiModelProperty(value = "是否使用白鹭厅", example = "0" ,required = true)
    private Integer hall;
    /**
     * 备注
     */
    @ApiModelProperty(value = "备注", example = "这是备注" ,required = true)
    private String remark;

}
