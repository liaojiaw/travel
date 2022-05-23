package com.xmairtravel.core.server.manager.entity.vo.in;

import com.xmairtravel.core.common.entity.BaseInVO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class QueryCustomerRequest  extends BaseInVO {
    @ApiModelProperty(value = "客户名称", example = "张三", required = true)
    private Integer customerName;
    @ApiModelProperty(value = "客户名称", example = "张三", required = true)
    private String phoneNumber;
}
