package com.sgai.pox.admin.core.base;

import com.sgai.pox.engine.core.session.Authority;
import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @author pox
 */
@Data
public class UserInfo implements Serializable {

    /**
     * 用户基本信息
     */
    private String userId;

    private String userName;

    private String password;

    private String orgId;

    private String roleId;

    private Map<String, Object> additionalInformation;

    /**
     * 权限标识集合
     */
    private List<Authority> authorities;

    public UserInfo() {
    }

    public UserInfo(String userId, String userName, String password, String orgId, String roleId,
                    Map<String, Object> additionalInformation, List<Authority> authorities) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.orgId = orgId;
        this.roleId = roleId;
        this.additionalInformation = additionalInformation;
        this.authorities = authorities;
    }
}
