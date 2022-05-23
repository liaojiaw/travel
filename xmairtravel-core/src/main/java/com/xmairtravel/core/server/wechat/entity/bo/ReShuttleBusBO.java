package com.xmairtravel.core.server.wechat.entity.bo;

import com.xmairtravel.core.server.wechat.entity.vo.in.ShuttleBusRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * (ReShuttleBus)实体类
 *
 * @author makejava
 * @since 2022-04-26 09:37:37
 */
@Data
@Table(name = "re_shuttle_bus")
@NoArgsConstructor
@AllArgsConstructor
public class ReShuttleBusBO implements Serializable {
    private static final long serialVersionUID = -49474801644783587L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer shuttleBusId;
    /**
     * 车牌号
     */
    private String plateNumber;
    /**
     * 车身颜色
     */
    private String bodyColor;
    /**
     * 核载人数
     */
    private Integer seats;
    /**
     * 品牌
     */
    private String brand;
    /**
     * 0 暂停运行 1 空闲中 2 载客中
     */
    private Integer status;
    /**
     * 司机ID  对应 re_user中user_id
     */
    private Integer driverId;


    public ReShuttleBusBO(ShuttleBusRequest shuttleBusRequest) {
        this.shuttleBusId = shuttleBusRequest.getShuttleBusId();
        this.seats = shuttleBusRequest.getSeats();
        this.bodyColor = shuttleBusRequest.getBodyColor();
        this.brand = shuttleBusRequest.getBrand();
        this.driverId = shuttleBusRequest.getDriverId();
        this.plateNumber = shuttleBusRequest.getPlateNumber();
        this.status = shuttleBusRequest.getStatus();
    }
}

