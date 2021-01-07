package com.sgai.pox.engine.service.sys;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sgai.pox.engine.common.core.base.BaseService;
import com.sgai.pox.engine.entity.sys.SysPost;
import com.sgai.pox.engine.entity.sys.SysPostUser;
import com.sgai.pox.engine.entity.sys.SysUser;
import org.flowable.idm.engine.impl.GroupQueryImpl;

import java.util.List;

/**
 * 岗位Service
 *
 * @author pox
 */
public interface SysPostService extends BaseService<SysPost> {

    /**
     * 分页查询岗位
     *
     * @param page
     * @param sysPost
     * @return
     */
    IPage<SysPost> list(IPage<SysPost> page, SysPost sysPost);

    /**
     * 查询岗位用户
     *
     * @param page
     * @param sysPostUser
     * @return
     */
    IPage<SysUser> getPostUser(Page<SysUser> page, SysPostUser sysPostUser);

    /**
     * 保存岗位用户
     *
     * @param postId
     * @param userIds
     */
    void savePostUsers(String postId, String userIds);

    /**
     * 删除岗位用户
     *
     * @param postId
     * @param userIds
     */
    void deletePostUsers(String postId, String userIds);

    /**
     * 删除岗位
     *
     * @param ids
     */
    void delete(String ids);

    /**
     * 根据Flowable GroupQueryImpl查询岗位列表
     *
     * @param query
     * @return
     */
    List<SysPost> getPostsByFlowableGroupQueryImpl(GroupQueryImpl query);
}
