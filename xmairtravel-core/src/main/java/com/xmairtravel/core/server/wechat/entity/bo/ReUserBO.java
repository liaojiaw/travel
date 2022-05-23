package com.xmairtravel.core.server.wechat.entity.bo;

import com.xmairtravel.core.server.wechat.entity.vo.in.UserRequest;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * (ReUser)实体类
 *
 * @author makejava
 * @since 2022-04-24 16:07:23
 */
@Data
@Table(name = "re_user")
@NoArgsConstructor
public class ReUserBO implements Serializable {
    private static final long serialVersionUID = 482762826256610668L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;
    /**
     * 用户姓名
     */
    private String userName;
    /**
     * 员工工号
     */
    private String staffCode;
    /**
     * 手机号码
     */
    private String mobile;
    /**
     * 0 删除 1 正常 2 冻结
     */
    private Integer status;
    /**
     * 用户身份  对应 re_identity中identity_id
     */
    private Integer identityId;
    /**
     * 小程序openid
     */
    private String openid;

    public ReUserBO(UserRequest userRequest) {
        this.userId = userRequest.getUserId();
        this.userName = userRequest.getUserName();
        this.staffCode = userRequest.getStaffCode();
        this.mobile = userRequest.getMobile();
        this.openid = userRequest.getOpenid();
        this.status = userRequest.getStatus();
        this.identityId = userRequest.getIdentityId();
    }
}

