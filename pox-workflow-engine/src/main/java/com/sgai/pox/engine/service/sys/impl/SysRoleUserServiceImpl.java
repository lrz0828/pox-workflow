package com.sgai.pox.engine.service.sys.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sgai.pox.engine.common.core.base.BaseServiceImpl;
import com.sgai.pox.engine.entity.sys.SysRoleUser;
import com.sgai.pox.engine.mapper.sys.SysRoleUserMapper;
import com.sgai.pox.engine.service.sys.SysRoleUserService;
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
