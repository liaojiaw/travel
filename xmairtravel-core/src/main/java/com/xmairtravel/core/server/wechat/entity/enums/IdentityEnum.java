package com.xmairtravel.core.server.wechat.entity.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum IdentityEnum {
    //0 删除 1 正常 2 冻结
    EMPLOYEE("员工"),
    CUSTOMER("客户");

    private String identityName;

    public String identityName(){
        return this.identityName;
    }
}