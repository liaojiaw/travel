package com.xmairtravel.core.server.wechat.entity.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum UserStatusEnum {
    //0 删除 1 正常 2 冻结
    DELETE(0,"删除"),
    NOMAL(1,"正常"),
    BLOCKING(2,"冻结");

    private Integer status;
    private String statusName;


    public static Integer getStatus(Integer status) {
        UserStatusEnum[] businessModeEnums = values();
        for (UserStatusEnum shuttleBusStatusEnum : businessModeEnums) {
            if (shuttleBusStatusEnum.status().equals(status)) {
                return shuttleBusStatusEnum.status();
            }
        }
        return null;
    }

    public static String getStatusName(Integer status) {
        UserStatusEnum[] businessModeEnums = values();
        for (UserStatusEnum shuttleBusStatusEnum : businessModeEnums) {
            if (shuttleBusStatusEnum.status().equals(status)) {
                return shuttleBusStatusEnum.statusName();
            }
        }
        return null;
    }

    public Integer status(){
        return this.status;
    }

    public String statusName(){
        return this.statusName;
    }
}