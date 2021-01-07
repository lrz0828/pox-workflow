package  com.sgai.pox.engine.mapper.sys;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sgai.pox.engine.entity.sys.SysRolePermission;

/**
 * 操作权限Mapper
 *
 * @author pox
 */
public interface SysRolePermissionMapper extends BaseMapper<SysRolePermission> {
    /**
     * 查询操作权限列表
     *
     * @param page
     * @param entity
     * @return
     */
    public List<SysRolePermission> list(IPage<SysRolePermission> page, @Param("entity") SysRolePermission entity);
}
