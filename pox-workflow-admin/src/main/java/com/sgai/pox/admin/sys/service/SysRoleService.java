package com.sgai.pox.admin.sys.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sgai.pox.engine.common.core.base.BaseService;
import com.sgai.pox.admin.sys.entity.SysRole;
import com.sgai.pox.admin.sys.entity.SysRoleUser;
import com.sgai.pox.admin.sys.entity.SysUser;

import java.util.Map;

/**
 * 角色Service
 *
 * @author pox
 */
public interface SysRoleService extends BaseService<SysRole> {

    /**
     * 分页查询角色
     *
     * @param page
     * @param sysRole
     * @return
     */
    IPage<SysRole> list(IPage<SysRole> page, SysRole sysRole);

    /**
     * 查询角色权限
     *
     * @param sysUser
     * @param roleId
     * @return
     */
    Map<String, Object> getRolePermissions(String roleId);

    /**
     * 保存角色权限
     *
     * @param roleId
     * @param menuOrFuncIds
     * @param permissionTypes
     */
    void saveRolePermissions(String roleId, String menuOrFuncIds, String permissionTypes);

    /**
     * 查询角色用户
     *
     * @param page
     * @param sysRoleUser
     * @return
     */
    IPage<SysUser> getRoleUser(Page<SysUser> page, SysRoleUser sysRoleUser);

    /**
     * 保存角色用户
     *
     * @param roleId
     * @param userIds
     */
    void saveRoleUsers(String roleId, String userIds);

    /**
     * 删除角色用户
     *
     * @param roleId
     * @param userIds
     */
    void deleteRoleUsers(String roleId, String userIds);

    /**
     * 删除角色
     *
     * @param ids
     */
    void delete(String ids);
}
