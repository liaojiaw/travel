package com.xmairtravel.core.server.wechat.entity.bo;

import lombok.Data;

import javax.persistence.Table;
import java.io.Serializable;

/**
 * (ReIdentity)实体类
 *
 * @author makejava
 * @since 2022-04-24 16:07:23
 */
@Data
@Table(name = "re_identity")
public class ReIdentityBO implements Serializable {
    private static final long serialVersionUID = -68046570459194058L;
    
    private Integer identityId;
    /**
     * 身份名称
     */
    private String identityName;
    /**
     * 身份代码
     */
    private String identityCode;

}

