package com.xmairtravel.core.server.wechat.entity.bo;

import lombok.Data;

import javax.persistence.Table;
import java.io.Serializable;

/**
 * (ReClaim)实体类
 *
 * @author makejava
 * @since 2022-04-24 16:07:22
 */
@Data
@Table(name = "re_claim")
public class ReClaimBO implements Serializable {
    private static final long serialVersionUID = -60584532009844110L;

    private Integer claimId;
    /**
     * 认领人ID
     */
    private Integer claimUserId;
    /**
     * 被认领人ID
     */
    private Integer claimedUserId;
    /**
     * 状态 0 取消 1完成
     */
    private Integer status;
    /**
     * 创建时间
     */
    private Long createTime;
    /**
     * 更新时间
     */
    private Long updateTime;

}

