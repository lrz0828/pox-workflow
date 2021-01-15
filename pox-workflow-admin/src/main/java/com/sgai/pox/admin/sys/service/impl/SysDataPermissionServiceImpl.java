package com.sgai.pox.admin.sys.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sgai.pox.engine.core.base.BaseServiceImpl;
import com.sgai.pox.admin.sys.entity.SysDataPermission;
import com.sgai.pox.admin.sys.mapper.SysDataPermissionMapper;
import com.sgai.pox.admin.sys.service.SysDataPermissionService;
import org.springframework.stereotype.Service;

/**
 * 数据权限Service
 *
 * @author pox
 */
@Service
public class SysDataPermissionServiceImpl extends BaseServiceImpl<SysDataPermissionMapper, SysDataPermission> implements SysDataPermissionService {
    @Override
    public IPage<SysDataPermission> list(IPage<SysDataPermission> page, SysDataPermission sysDataPermission) {
        return page.setRecords(baseMapper.list(page, sysDataPermission));
    }
}
