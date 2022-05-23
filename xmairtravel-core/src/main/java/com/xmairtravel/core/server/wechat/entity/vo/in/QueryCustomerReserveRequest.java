package com.xmairtravel.core.server.wechat.entity.vo.in;

import com.xmairtravel.core.common.entity.BaseInVO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class QueryCustomerReserveRequest extends BaseInVO {
    @ApiModelProperty(value = "开始日期", example = "1")
    private Long reserveStartTime;
    @ApiModelProperty(value = "结束日期", example = "1")
    private Long reserveEndTime;
    @ApiModelProperty(value = "状态 0 取消 1 进行中 2已完成", example = "1")
    private Integer status;
    @ApiModelProperty(value = "openid", example = "1")
    private String openid;
}
