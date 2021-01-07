package com.sgai.pox.admin.sys.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sgai.pox.engine.common.core.aspect.annotation.DataPermission;
import com.sgai.pox.admin.sys.entity.SysMenu;
import com.sgai.pox.admin.sys.entity.SysRole;
import com.sgai.pox.admin.sys.entity.SysUser;
import com.sgai.pox.admin.sys.entity.vo.SysRolePermissionVO;
import com.sgai.pox.engine.provider.OrgDataPermissionProvider;

/**
 * 用户Mapper
 *
 * @author pox
 */
public interface SysUserMapper extends BaseMapper<SysUser> {
    /**
     * 查询用户列表
     *
     * @param page
     * @param entity
     * @return
     */
    @DataPermission(providers = OrgDataPermissionProvider.class, providerParams = "{\"alias\":\"o\",\"type\":\"1\"}",
            fieldName = "authFilterSql")
    public List<SysUser> list(IPage<SysUser> page, @Param("entity") SysUser entity);

    /**
     * 根据用户Id查询角色列表
     *
     * @param userId
     * @return
     */
    public List<SysRole> getRolesByUserId(@Param("userId") String userId);

    /**
     * 查询所有权限清单
     *
     * @return
     */
    public List<SysRolePermissionVO> getAdminPermissions();

    /**
     * 根据角色Id查询角色权限清单
     *
     * @param roleId
     * @return
     */
    public List<SysRolePermissionVO> getRolePermissions(@Param("roleId") String roleId);

    /**
     * 根据角色Id查询菜单列表
     *
     * @param roleId
     * @return
     */
    public List<SysMenu> getRoleMenus(@Param("roleId") String roleId);
}
