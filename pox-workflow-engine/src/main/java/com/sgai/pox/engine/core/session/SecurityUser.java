package com.sgai.pox.engine.core.session;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

/**
 * 扩展用户信息
 *
 * @author pox
 */
public class SecurityUser extends User {
    private static final long serialVersionUID = 1L;

    @Getter
    private String roleId;
    @Getter
    private String orgId;
    @Getter
    private String userRealName;
    @Getter
    private Map<String, Object> additionalInformation;
    @Getter
    private String access_token;



    public SecurityUser(String roleId, String orgId, String userRealName, Map<String, Object> additionalInformation,
                        String username, String password, boolean enabled, boolean accountNonExpired,
                        boolean credentialsNonExpired, boolean accountNonLocked,  List<Authority> authorities,
                        String access_token) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        this.roleId = roleId;
        this.orgId = orgId;
        this.userRealName = userRealName;
        this.additionalInformation = additionalInformation;
        this.access_token = access_token;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return super.isAccountNonExpired();
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return super.isAccountNonLocked();
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return super.isCredentialsNonExpired();
    }

    @JsonIgnore
    @Override
    public boolean isEnabled() {
        return super.isEnabled();
    }
}
