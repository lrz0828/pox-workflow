package com.sgai.pox.admin.sys.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sgai.pox.engine.common.core.base.BaseService;
import com.sgai.pox.admin.sys.entity.SysRolePermission;

/**
 * 操作权限Service
 *
 * @author pox
 */
public interface SysRolePermissionService extends BaseService<SysRolePermission> {
    /**
     * 分页查询操作权限
     *
     * @param page
     * @param sysRolePermission
     * @return
     */
    IPage<SysRolePermission> list(IPage<SysRolePermission> page, SysRolePermission sysRolePermission);
}
