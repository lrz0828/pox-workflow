package  com.sgai.pox.engine.mapper.sys;

import java.util.List;

import com.sgai.pox.engine.provider.OrgDataPermissionProvider;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sgai.pox.engine.common.core.aspect.annotation.DataPermission;
import com.sgai.pox.engine.entity.sys.SysOrg;

/**
 * 机构Mapper
 *
 * @author pox
 */
public interface SysOrgMapper extends BaseMapper<SysOrg> {
    /**
     * 查询机构列表
     *
     * @param page
     * @param entity
     * @return
     */
    @DataPermission(providers = OrgDataPermissionProvider.class)
    public List<SysOrg> list(IPage<SysOrg> page, @Param("entity") SysOrg entity);
}
