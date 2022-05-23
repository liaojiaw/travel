package com.xmairtravel.core.server.wechat.entity.dto;

import com.xmairtravel.core.server.wechat.entity.bo.ReReserveBO;
import com.xmairtravel.core.server.wechat.entity.enums.ReserveStatusEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class ReReserveDTO {

    public ReReserveDTO(ReReserveBO reReserveBO){
        this.hall = reReserveBO.getHall();
        this.flight = reReserveBO.getFlight();
        this.remark = reReserveBO.getRemark();
        this.openid = reReserveBO.getOpenid();
        this.reserveId = reReserveBO.getReserveId();
        this.departure = reReserveBO.getDeparture();
        this.createTime = reReserveBO.getCreateTime();
        this.updateTime = reReserveBO.getUpdateTime();
        this.customerId = reReserveBO.getCustomerId();
        this.businessId = reReserveBO.getBusinessId();
        this.destination = reReserveBO.getDestination();
        this.customerName = reReserveBO.getCustomerName();
        this.businessName = reReserveBO.getBusinessName();
        this.reserveEndTime = reReserveBO.getReserveEndTime();
        this.reserveStartTime = reReserveBO.getReserveStartTime();
        this.departureCoordinate = reReserveBO.getDepartureCoordinate();
        this.destinationCoordinate = reReserveBO.getDestinationCoordinate();
        this.statusName = ReserveStatusEnum.getStatusName(reReserveBO.getStatus());

    }

    public static List<ReReserveDTO> copyPorperties(List<ReReserveBO> reReserveBOS){
        return reReserveBOS.stream().map(reUserBO -> {
            return new ReReserveDTO(reUserBO);
        }).collect(Collectors.toList());
    }

    @ApiModelProperty(value = "主键 新增时传空", example = "1")
    private Integer reserveId;
    /**
     * 预定开始时间
     */
    @ApiModelProperty(value = "预定开始时间", example = "1")
    private Long reserveStartTime;
    /**
     * 预定结束时间
     */
    @ApiModelProperty(value = "预定结束时间", example = "1")
    private Long reserveEndTime;
    /**
     * 状态 0 取消 1正常 2被认领
     */
    @ApiModelProperty(value = "状态 0 取消 1 进行中 2已完成", example = "1")
    private String statusName;
    /**
     * 小程序唯一标识
     */
    @ApiModelProperty(value = "小程序唯一标识", example = "1")
    private String openid;
    /**
     * 用户ID
     */
    @ApiModelProperty(value = "用户ID", example = "1")
    private Integer getCustomerId;
    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间", example = "1")
    private Long createTime;
    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间", example = "1")
    private Long updateTime;
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
     * 业务人员ID
     */
    @ApiModelProperty(value = "业务人员ID", example = "1")
    private Integer businessId;
    /**
     * 业务人员ID
     */
    @ApiModelProperty(value = "业务人员ID", example = "1")
    private Integer customerId;
    /**
     * 业务人员名称
     */
    @ApiModelProperty(value = "业务人员名称", example = "张三")
    private String businessName;
    /**
     * 客户名称
     */
    @ApiModelProperty(value = "客户名称", example = "李四")
    private String customerName;
    /**
     * 航班号
     */
    @ApiModelProperty(value = "航班号", example = "ABC123" ,required = true)
    private String flight;

    /**
     * 是否使用白鹭厅
     */
    @ApiModelProperty(value = "是否使用白鹭厅", example = "0 否 1 是" ,required = true)
    private Integer hall;
    /**
     * 备注
     */
    @ApiModelProperty(value = "备注", example = "这是备注" ,required = true)
    private String remark;

}
