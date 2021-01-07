package com.sgai.pox.admin.sys.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sgai.pox.engine.common.core.base.BaseServiceImpl;
import com.sgai.pox.admin.sys.entity.SysRolePermission;
import com.sgai.pox.admin.sys.mapper.SysRolePermissionMapper;
import com.sgai.pox.admin.sys.service.SysRolePermissionService;
import org.springframework.stereotype.Service;

/**
 * 操作权限Service
 *
 * @author pox
 */
@Service
public class SysRolePermissionServiceImpl extends BaseServiceImpl<SysRolePermissionMapper, SysRolePermission> implements SysRolePermissionService {
    @Override
    public IPage<SysRolePermission> list(IPage<SysRolePermission> page, SysRolePermission sysRolePermission) {
        return page.setRecords(baseMapper.list(page, sysRolePermission));
    }
}
