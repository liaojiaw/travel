package com.xmairtravel.core.server.wechat.entity.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum HallEnum {
    //0 取消 1正常 2被认领
    NO(0, "否"),
    YES(1, "是");

    private Integer status;
    private String statusName;


    public static Integer getStatus(Integer status) {
        HallEnum[] businessModeEnums = values();
        for (HallEnum shuttleBusStatusEnum : businessModeEnums) {
            if (shuttleBusStatusEnum.status().equals(status)) {
                return shuttleBusStatusEnum.status();
            }
        }
        return null;
    }

    public static String getStatusName(Integer status) {
        HallEnum[] businessModeEnums = values();
        for (HallEnum shuttleBusStatusEnum : businessModeEnums) {
            if (shuttleBusStatusEnum.status().equals(status)) {
                return shuttleBusStatusEnum.statusName();
            }
        }
        return null;
    }

    public Integer status() {
        return this.status;
    }

    public String statusName() {
        return this.statusName;
    }
}
