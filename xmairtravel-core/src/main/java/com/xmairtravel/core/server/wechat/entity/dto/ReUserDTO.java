package com.xmairtravel.core.server.wechat.entity.dto;

import com.xmairtravel.core.server.wechat.entity.bo.ReUserBO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class ReUserDTO {

    public ReUserDTO(ReUserBO reUserBO){
        this.userId = reUserBO.getUserId();
        this.userName = reUserBO.getUserName();
        this.staffCode = reUserBO.getStaffCode();
        this.mobile = reUserBO.getMobile();
    }

    public static List<ReUserDTO> copyPorperties(List<ReUserBO> reUserBOS){
        return reUserBOS.stream().map(reUserBO -> {
            return new ReUserDTO(reUserBO);
        }).collect(Collectors.toList());
    }

    @ApiModelProperty(value = "主键 新增时传空", example = "1")
    private Integer userId;
    /**
     * 用户姓名
     */
    @ApiModelProperty(value = "用户姓名", example = "张三" ,required = true)
    private String userName;
    /**
     * 员工工号
     */
    @ApiModelProperty(value = "员工工号", example = "94999" )
    private String staffCode;
    /**
     * 手机号码
     */
    @ApiModelProperty(value = "手机号码", example = "12345678901" ,required = true)
    private String mobile;

}
