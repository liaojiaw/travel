package com.xmairtravel.core.server.manager.entity.dto;

import com.xmairtravel.core.server.wechat.entity.bo.ReCustomerBO;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReCustomerDTO {

    public ReCustomerDTO(ReCustomerBO reCustomerBO){
        this.customerId = reCustomerBO.getCustomerId();
        this.customerName = reCustomerBO.getCustomerName();
        this.openid = reCustomerBO.getOpenid();
        this.sex = reCustomerBO.getSex();
        this.phoneNum = reCustomerBO.getPhoneNum();
    }

    public static List<ReCustomerDTO> copyPorperties(List<ReCustomerBO> reCustomerBOS){
        return reCustomerBOS.stream().map(reCustomerBO -> {
            return new ReCustomerDTO(reCustomerBO);
        }).collect(Collectors.toList());
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "主键 新增时传空", example = "1")
    private Integer customerId;

    @ApiModelProperty(value = "客户姓名", example = "张三")
    private String customerName;

    @ApiModelProperty(value = "业务人员名称", example = "张三")
    private String businessName;

    @ApiModelProperty(value = "手机号码", example = "1123434546")
    private String phoneNum;

    @ApiModelProperty(value = "性别(0=男,1=女,2=未知)", example = "1")
    private Integer sex;

    @ApiModelProperty(value = "openid", example = "1")
    private String openid;
}
