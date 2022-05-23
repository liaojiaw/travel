package com.xmairtravel.core.server.wechat.entity.vo.in;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@ApiModel(description = "接驳车入参")
public class ShuttleBusRequest {

    @ApiModelProperty(value = "主键 新增时传空", example = "1")
    private Integer shuttleBusId;
    /**
     * 车牌号
     */
    @NotNull
    @ApiModelProperty(value = "车牌号", example = "湘F999999" ,required = true)
    private String plateNumber;
    /**
     * 车身颜色
     */
    @NotNull
    @ApiModelProperty(value = "车身颜色", example = "土豪金",required = true)
    private String bodyColor;
    /**
     * 核载人数
     */
    @NotNull
    @ApiModelProperty(value = "核载人数", example = "5",required = true)
    private Integer seats;
    /**
     * 品牌
     */
    @NotNull
    @ApiModelProperty(value = "品牌", example = "劳斯莱斯",required = true)
    private String brand;
    /**
     * 0 暂停运行 1 空闲中 2 载客中
     */
    @ApiModelProperty(value = "0 暂停运行 1 空闲中 2 载客中", example = "1")
    private Integer status;
    /**
     * 司机ID  对应 re_user中user_id
     */
    @NotNull
    @ApiModelProperty(value = "司机ID", example = "1",required = true)
    private Integer driverId;
}
