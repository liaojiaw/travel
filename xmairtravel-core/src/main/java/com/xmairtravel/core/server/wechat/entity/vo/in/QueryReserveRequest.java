package com.xmairtravel.core.server.wechat.entity.vo.in;

import com.xmairtravel.core.common.entity.BaseInVO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class QueryReserveRequest extends BaseInVO {
    @ApiModelProperty(value = "开始日期", example = "1")
    private Long reserveStartTime;
    @ApiModelProperty(value = "结束日期", example = "1")
    private Long reserveEndTime;
    @ApiModelProperty(value = "状态 0 取消 1 进行中 2已完成", example = "1")
    private Integer status;
    @ApiModelProperty(value = "是否使用白鹭厅", example = "0 否 1 是")
    private Integer hall;
    @ApiModelProperty(value = "客户ID", example = "1")
    private String customerId;
    @ApiModelProperty(value = "openid", example = "1")
    private String openid;
    @ApiModelProperty(value = "客户名称", example = "张三")
    private String customerName;
}
