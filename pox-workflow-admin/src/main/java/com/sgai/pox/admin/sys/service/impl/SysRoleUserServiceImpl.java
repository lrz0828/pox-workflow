package com.sgai.pox.admin.sys.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sgai.pox.engine.common.core.base.BaseServiceImpl;
import com.sgai.pox.admin.sys.entity.SysRoleUser;
import com.sgai.pox.admin.sys.mapper.SysRoleUserMapper;
import com.sgai.pox.admin.sys.service.SysRoleUserService;
import org.springframework.stereotype.Service;

/**
 * 角色和用户关系Service
 *
 * @author pox
 */
@Service
public class SysRoleUserServiceImpl extends BaseServiceImpl<SysRoleUserMapper, SysRoleUser> implements SysRoleUserService {
    @Override
    public IPage<SysRoleUser> list(IPage<SysRoleUser> page, SysRoleUser sysRoleUser) {
        return page.setRecords(baseMapper.list(page, sysRoleUser));
    }
}
