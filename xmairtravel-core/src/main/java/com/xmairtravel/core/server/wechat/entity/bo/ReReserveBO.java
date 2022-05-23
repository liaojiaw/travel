package com.xmairtravel.core.server.wechat.entity.bo;

import io.swagger.models.auth.In;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * (ReReserve)实体类
 *
 * @author makejava
 * @since 2022-04-24 16:07:23
 */
@Data
@Table(name = "re_reserve")
public class ReReserveBO implements Serializable {
    private static final long serialVersionUID = 456223265836386339L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer reserveId;
    /**
     * 预定开始时间
     */
    private Long reserveStartTime;
    /**
     * 预定结束时间
     */
    private Long reserveEndTime;
    /**
     * 状态 0 取消 1正常 2被认领
     */
    private Integer status;
    /**
     * 小程序唯一标识
     */
    private String openid;
    /**
     * 用户ID
     */
    private Integer customerId;
    /**
     * 创建时间
     */
    private Long createTime;
    /**
     * 更新时间
     */
    private Long updateTime;
    /**
     * 出发地
     */
    private String departure;
    /**
     * 目的地
     */
    private String destination;
    /**
     * 出发地坐标
     */
    private String departureCoordinate;
    /**
     * 目的地坐标
     */
    private String destinationCoordinate;
    /**
     * 业务人员ID
     */
    private Integer businessId;
    /**
     * 业务人员名称
     */
    private String businessName;
    /**
     * 客户名称
     */
    private String customerName;
    /**
     * 航班号
     */
    private String flight;
    /**
  * 是否使用白鹭厅
     */
    private Integer hall;
    /**
     * 目的地坐标
     */
    private String remark;


}

