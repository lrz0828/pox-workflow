package com.sgai.pox.admin.sys.entity.vo;

import com.sgai.pox.admin.sys.entity.SysOrg;
import com.sgai.pox.admin.sys.entity.SysRole;
import com.sgai.pox.admin.sys.entity.SysUser;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @author pox
 * @date 2021年01月04日
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
