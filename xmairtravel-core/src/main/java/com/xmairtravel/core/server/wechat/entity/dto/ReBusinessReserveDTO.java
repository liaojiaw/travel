package com.xmairtravel.core.server.wechat.entity.dto;

import com.xmairtravel.core.server.wechat.entity.bo.ReReserveBO;
import com.xmairtravel.core.server.wechat.entity.enums.HallEnum;
import com.xmairtravel.core.server.wechat.entity.enums.ReserveStatusEnum;
import com.xmairtravel.core.utils.DateUtils;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class ReBusinessReserveDTO {

    public ReBusinessReserveDTO(ReReserveBO reReserveBO){
        this.flight = reReserveBO.getFlight();
        this.remark = reReserveBO.getRemark();
        this.reserveId = reReserveBO.getReserveId();
        this.departure = reReserveBO.getDeparture();
        this.destination = reReserveBO.getDestination();
        this.customerName = reReserveBO.getCustomerName();
        this.hall = HallEnum.getStatusName(reReserveBO.getHall());
        this.departureCoordinate = reReserveBO.getDepartureCoordinate();
        this.destinationCoordinate = reReserveBO.getDestinationCoordinate();
        this.statusName = ReserveStatusEnum.getStatusName(reReserveBO.getStatus());
        this.createTime = DateUtils.toDate(reReserveBO.getCreateTime(),1000);
        this.updateTime = DateUtils.toDate(reReserveBO.getUpdateTime(),1000);
        this.reserveEndTime =   DateUtils.toDate(reReserveBO.getReserveEndTime(),1000);
        this.reserveStartTime = DateUtils.toDate(reReserveBO.getReserveStartTime(),1000);

    }

    public static List<ReBusinessReserveDTO> copyPorperties(List<ReReserveBO> reReserveBOS){
        return reReserveBOS.stream().map(reUserBO -> {
            return new ReBusinessReserveDTO(reUserBO);
        }).collect(Collectors.toList());
    }

    @ApiModelProperty(value = "主键 新增时传空", example = "1")
    private Integer reserveId;
    /**
     * 预定开始时间
     */
    @ApiModelProperty(value = "预定开始时间", example = "1")
    private String reserveStartTime;
    /**
     * 预定结束时间
     */
    @ApiModelProperty(value = "预定结束时间", example = "1")
    private String reserveEndTime;
    /**
     * 状态 0 取消 1正常 2被认领
     */
    @ApiModelProperty(value = "状态 0 取消 1 进行中 2已完成", example = "1")
    private String statusName;
    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间", example = "1")
    private String createTime;
    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间", example = "1")
    private String updateTime;
    /**
     * 出发地
     */
    @ApiModelProperty(value = "出发地", example = "1")
    private String departure;
    /**
     * 目的地
     */
    @ApiModelProperty(value = "目的地", example = "1")
    private String destination;
    /**
     * 出发地坐标
     */
    @ApiModelProperty(value = "出发地坐标", example = "1")
    private String departureCoordinate;
    /**
     * 目的地坐标
     */
    @ApiModelProperty(value = "目的地坐标", example = "1")
    private String destinationCoordinate;
    /**
     * 航班号
     */
    @ApiModelProperty(value = "航班号", example = "ABC123" ,required = true)
    private String flight;
    /**
     * 备注
     */
    @ApiModelProperty(value = "备注", example = "这是备注" ,required = true)
    private String remark;

    /**
     * 是否使用白鹭厅
     */
    @ApiModelProperty(value = "是否使用白鹭厅", example = "0 否 1 是" ,required = true)
    private String hall;

    /**
     * 客户名称
     */
    @ApiModelProperty(value = "客户名称", example = "李四")
    private String customerName;

}
