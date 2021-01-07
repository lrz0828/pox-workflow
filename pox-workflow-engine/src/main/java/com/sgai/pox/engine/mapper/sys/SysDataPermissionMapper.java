package  com.sgai.pox.engine.mapper.sys;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sgai.pox.engine.entity.sys.SysDataPermission;

/**
 * 数据权限Mapper
 *
 * @author pox
 */
public interface SysDataPermissionMapper extends BaseMapper<SysDataPermission> {
    /**
     * 查询数据权限列表
     *
     * @param page
     * @param entity
     * @return
     */
    public List<SysDataPermission> list(IPage<SysDataPermission> page, @Param("entity") SysDataPermission entity);
}
