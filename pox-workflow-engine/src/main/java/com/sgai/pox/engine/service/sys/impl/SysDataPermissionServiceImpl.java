package com.sgai.pox.engine.service.sys.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sgai.pox.engine.common.core.base.BaseServiceImpl;
import com.sgai.pox.engine.entity.sys.SysDataPermission;
import com.sgai.pox.engine.mapper.sys.SysDataPermissionMapper;
import com.sgai.pox.engine.service.sys.SysDataPermissionService;
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
