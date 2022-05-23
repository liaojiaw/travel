package com.xmairtravel.core.common.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class BaseInVO {
    @ApiModelProperty(value = "查询页面，从1开始", example = "1", required = true)
    @NotNull(message = "页面信息不能为空")
    @Min(value = 1, message = "页面信息有误")
    private Integer pageNum;

    @ApiModelProperty(value = "每页返回条数，大于0", example = "10", required = true)
    @NotNull(message = "页面信息不能为空")
    @Min(value = 1, message = "页面信息有误")
    private Integer pageSize;

}
