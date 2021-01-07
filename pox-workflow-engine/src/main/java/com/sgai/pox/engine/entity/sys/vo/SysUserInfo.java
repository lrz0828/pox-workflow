package com.sgai.pox.engine.entity.sys.vo;

import com.sgai.pox.engine.entity.sys.SysOrg;
import com.sgai.pox.engine.entity.sys.SysRole;
import com.sgai.pox.engine.entity.sys.SysUser;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @author pox
 * @date 2020年3月24日
 */
public class SysUserInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Getter
    private SysUser sysUser;
    @Getter
    private SysOrg sysOrg;
    @Getter
    private SysRole sysRole;
    @Getter
    private List<SysRole> sysRoles;
    @Getter
    private List<Route> routes;
    @Getter
    private Collection<? extends GrantedAuthority> authorities;

    public SysUserInfo(SysUser sysUser, SysOrg sysOrg, SysRole sysRole, List<SysRole> sysRoles, List<Route> routes,
                       Collection<? extends GrantedAuthority> authorities) {
        this.sysUser = sysUser;
        this.sysOrg = sysOrg;
        this.sysRole = sysRole;
        this.sysRoles = sysRoles;
        this.routes = routes;
        this.authorities = authorities;
    }
}
