package com.sgai.pox.engine.service.sys;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sgai.pox.engine.common.core.base.BaseService;
import com.sgai.pox.engine.entity.sys.SysRoleUser;

/**
 * 角色和用户关系Service
 *
 * @author pox
 */
public interface SysRoleUserService extends BaseService<SysRoleUser> {
    /**
     * 分页查询角色和用户关系
     *
     * @param page
     * @param sysRoleUser
     * @return
     */
    IPage<SysRoleUser> list(IPage<SysRoleUser> page, SysRoleUser sysRoleUser);
}
