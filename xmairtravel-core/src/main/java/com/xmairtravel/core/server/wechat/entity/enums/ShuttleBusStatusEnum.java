package com.xmairtravel.core.server.wechat.entity.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum ShuttleBusStatusEnum {
    PAUSE(0,"暂停服务"),
    LEISURE(1,"空闲中"),
    CAPACITY(2,"载客中");

    private Integer status;
    private String statusName;


    public static Integer getStatus(Integer status) {
        ShuttleBusStatusEnum[] businessModeEnums = values();
        for (ShuttleBusStatusEnum shuttleBusStatusEnum : businessModeEnums) {
            if (shuttleBusStatusEnum.status().equals(status)) {
                return shuttleBusStatusEnum.status();
            }
        }
        return null;
    }

    public static String getStatusName(Integer status) {
        ShuttleBusStatusEnum[] businessModeEnums = values();
        for (ShuttleBusStatusEnum shuttleBusStatusEnum : businessModeEnums) {
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