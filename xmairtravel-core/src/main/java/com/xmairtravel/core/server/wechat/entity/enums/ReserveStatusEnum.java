package com.xmairtravel.core.server.wechat.entity.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum ReserveStatusEnum {
    //0 取消 1正常 2被认领
    CANCEL(0, "取消"),
    ACTIVE(1, "进行中"),
    FINISH(2, "已完成");

    private Integer status;
    private String statusName;


    public static Integer getStatus(Integer status) {
        ReserveStatusEnum[] businessModeEnums = values();
        for (ReserveStatusEnum shuttleBusStatusEnum : businessModeEnums) {
            if (shuttleBusStatusEnum.status().equals(status)) {
                return shuttleBusStatusEnum.status();
            }
        }
        return null;
    }

    public static String getStatusName(Integer status) {
        ReserveStatusEnum[] businessModeEnums = values();
        for (ReserveStatusEnum shuttleBusStatusEnum : businessModeEnums) {
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
