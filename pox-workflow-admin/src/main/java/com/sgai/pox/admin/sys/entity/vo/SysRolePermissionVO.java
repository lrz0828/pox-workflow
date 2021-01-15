package com.sgai.pox.admin.sys.entity.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author pox
 * @date 2021年01月04日
 */
@Data
public class SysRolePermissionVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String rolePermissionId;
    private String roleId;
    private String permissionType;
    private String menuOrFuncId;
    private String menuUrl;
    private String menuPermissions;
    private String funcMenuId;
    private String funcPermissions;
}
