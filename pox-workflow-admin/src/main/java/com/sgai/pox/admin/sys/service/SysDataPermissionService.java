package com.sgai.pox.admin.sys.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sgai.pox.engine.core.base.BaseService;
import com.sgai.pox.admin.sys.entity.SysDataPermission;

/**
 * 数据权限Service
 *
 * @author pox
 */
public interface SysDataPermissionService extends BaseService<SysDataPermission> {
    /**
     * 分页查询数据权限
     *
     * @param page
     * @param sysDataPermission
     * @return
     */
    IPage<SysDataPermission> list(IPage<SysDataPermission> page, SysDataPermission sysDataPermission);
}
