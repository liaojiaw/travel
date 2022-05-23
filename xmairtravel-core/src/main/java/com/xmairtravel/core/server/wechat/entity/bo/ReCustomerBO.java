package com.xmairtravel.core.server.wechat.entity.bo;


import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table(name = "re_customer")
public class ReCustomerBO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer customerId;

    private String customerName;

    private String phoneNum;

    private Integer sex;

    private String openid;
}
