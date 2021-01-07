package com.sgai.pox.engine.service.sys;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sgai.pox.engine.common.core.base.BaseService;
import com.sgai.pox.engine.entity.sys.SysRolePermission;

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
