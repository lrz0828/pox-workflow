package  com.sgai.pox.engine.mapper.sys;

import java.util.List;

import com.sgai.pox.engine.provider.OrgDataPermissionProvider;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sgai.pox.engine.common.core.aspect.annotation.DataPermission;
import com.sgai.pox.engine.entity.sys.SysRole;
import com.sgai.pox.engine.entity.sys.SysRoleUser;
import com.sgai.pox.engine.entity.sys.SysUser;

/**
 * 角色Mapper
 *
 * @author pox
 */
public interface SysRoleMapper extends BaseMapper<SysRole> {
    /**
     * 查询角色列表
     *
     * @param page
     * @param entity
     * @return
     */
    public List<SysRole> list(IPage<SysRole> page, @Param("entity") SysRole entity);

    /**
     * 根据角色I点查询菜单按钮列表
     *
     * @param roleId
     * @return
     */
    public List<String> listMenuOrFuncIdsByRoleId(String roleId);

    /**
     * 查询角色用户列表
     *
     * @param page
     * @param entity
     * @return
     */
    @DataPermission(providers = OrgDataPermissionProvider.class)
    public List<SysUser> getRoleUser(IPage<SysUser> page, @Param("entity") SysRoleUser entity);
}
