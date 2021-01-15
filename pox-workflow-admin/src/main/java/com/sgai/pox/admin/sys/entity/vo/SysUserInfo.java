package com.sgai.pox.admin.sys.entity.vo;

import com.sgai.pox.admin.sys.entity.SysOrg;
import com.sgai.pox.admin.sys.entity.SysRole;
import com.sgai.pox.admin.sys.entity.SysUser;
import com.sgai.pox.engine.core.session.Authority;
import lombok.Getter;

import java.io.Serializable;
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
    private List<Authority> authorities;

    public SysUserInfo() {
    }

    public SysUserInfo(SysUser sysUser, SysOrg sysOrg, SysRole sysRole, List<SysRole> sysRoles, List<Route> routes, List<Authority> authorities) {
        this.sysUser = sysUser;
        this.sysOrg = sysOrg;
        this.sysRole = sysRole;
        this.sysRoles = sysRoles;
        this.routes = routes;
        this.authorities = authorities;
    }
}
