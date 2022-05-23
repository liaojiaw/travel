package com.xmairtravel.core.server.wechat.entity.dto;

import com.xmairtravel.core.server.wechat.entity.bo.ReShuttleBusBO;
import com.xmairtravel.core.server.wechat.entity.enums.ShuttleBusStatusEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class ReShuttleBusDTO {

    public ReShuttleBusDTO(ReShuttleBusBO reShuttleBusBO){
        this.shuttleBusId = reShuttleBusBO.getShuttleBusId();
        this.plateNumber = reShuttleBusBO.getPlateNumber();
        this.bodyColor = reShuttleBusBO.getBodyColor();
        this.seats = reShuttleBusBO.getSeats();
        this.brand = reShuttleBusBO.getBrand();
        this.statusName = ShuttleBusStatusEnum.getStatusName(reShuttleBusBO.getStatus());
        this.driverId = reShuttleBusBO.getDriverId();
    }

    public static List<ReShuttleBusDTO> copyPorperties(List<ReShuttleBusBO> reShuttleBusBOS){
        return reShuttleBusBOS.stream().map(reShuttleBusBO -> {
            return new ReShuttleBusDTO(reShuttleBusBO);
        }).collect(Collectors.toList());
    }

    @ApiModelProperty(value = "主键", example = "1")
    private Integer shuttleBusId;
    /**
     * 车牌号
     */
    @ApiModelProperty(value = "车牌号", example = "湘F999999")
    private String plateNumber;
    /**
     * 车身颜色
     */
    @ApiModelProperty(value = "车身颜色", example = "土豪金")
    private String bodyColor;
    /**
     * 核载人数
     */
    @ApiModelProperty(value = "核载人数", example = "5")
    private Integer seats;
    /**
     * 品牌
     */
    @ApiModelProperty(value = "品牌", example = "劳斯莱斯")
    private String brand;
    /**
     * 0 暂停运行 1 空闲中 2 载客中
     */
    @ApiModelProperty(value = "状态", example = "载客中")
    private String statusName;
    /**
     * 司机ID  对应 re_user中user_id
     */
    @ApiModelProperty(value = "司机ID", example = "1")
    private Integer driverId;

}
