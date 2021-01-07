package  com.sgai.pox.engine.mapper.sys;

import java.util.List;

import com.sgai.pox.engine.provider.OrgDataPermissionProvider;
import org.apache.ibatis.annotations.Param;
import org.flowable.idm.engine.impl.GroupQueryImpl;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sgai.pox.engine.common.core.aspect.annotation.DataPermission;
import com.sgai.pox.engine.entity.sys.SysPost;
import com.sgai.pox.engine.entity.sys.SysPostUser;
import com.sgai.pox.engine.entity.sys.SysUser;

/**
 * 岗位Mapper
 *
 * @author pox
 */
public interface SysPostMapper extends BaseMapper<SysPost> {
    /**
     * 查询岗位列表
     *
     * @param page
     * @param entity
     * @return
     */
    public List<SysPost> list(IPage<SysPost> page, @Param("entity") SysPost entity);

    /**
     * 查询岗位用户列表
     *
     * @param page
     * @param entity
     * @return
     */
    @DataPermission(providers = OrgDataPermissionProvider.class)
    public List<SysUser> getPostUser(IPage<SysUser> page, @Param("entity") SysPostUser entity);

    /**
     * 根据Flowable GroupQueryImpl查询岗位列表
     *
     * @param query
     * @return
     */
    public List<SysPost> getPostsByFlowableGroupQueryImpl(GroupQueryImpl query);
}
