package com.sgai.pox.admin.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sgai.pox.engine.common.core.base.BaseServiceImpl;
import com.sgai.pox.admin.sys.entity.SysPost;
import com.sgai.pox.admin.sys.entity.SysPostUser;
import com.sgai.pox.admin.sys.entity.SysUser;
import com.sgai.pox.admin.sys.mapper.SysPostMapper;
import com.sgai.pox.admin.sys.service.SysPostService;
import com.sgai.pox.admin.sys.service.SysPostUserService;
import org.flowable.idm.engine.impl.GroupQueryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

/**
 * 岗位Service
 *
 * @author pox
 */
@Service
public class SysPostServiceImpl extends BaseServiceImpl<SysPostMapper, SysPost> implements SysPostService {
    @Autowired
    private SysPostUserService sysPostUserService;

    @Override
    public IPage<SysPost> list(IPage<SysPost> page, SysPost sysPost) {
        return page.setRecords(baseMapper.list(page, sysPost));
    }

    /**
     * 查询岗位用户
     *
     * @param page
     * @param sysPostUser
     * @return
     */
    @Override
    public IPage<SysUser> getPostUser(Page<SysUser> page, SysPostUser sysPostUser) {
        return page.setRecords(baseMapper.getPostUser(page, sysPostUser));
    }

    /**
     * 保存岗位用户
     *
     * @param postId
     * @param userIds
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void savePostUsers(String postId, String userIds) {
        String[] userIdArray = userIds.split(",");
        // 【1】先删除岗位用户
        QueryWrapper<SysPostUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("POST_ID", postId).in("USER_ID", (Object[]) userIdArray);
        this.sysPostUserService.remove(queryWrapper);

        // 【2】保存岗位用户
        for (int i = 0; i < userIdArray.length; i++) {
            SysPostUser sysPostUser = new SysPostUser(postId, userIdArray[i]);
            this.sysPostUserService.save(sysPostUser);
        }
    }

    /**
     * 删除岗位用户
     *
     * @param postId
     * @param userIds
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deletePostUsers(String postId, String userIds) {
        QueryWrapper<SysPostUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("POST_ID", postId).in("USER_ID", (Object[]) userIds.split(","));
        this.sysPostUserService.remove(queryWrapper);
    }

    /**
     * 删除岗位
     *
     * @param ids
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(String ids) {
        String[] idsArr = ids.split(",");
        if (idsArr.length > 1) {
            this.removeByIds(Arrays.asList(idsArr));
        } else {
            this.removeById(idsArr[0]);
        }
        this.sysPostUserService.remove(new QueryWrapper<SysPostUser>().in("POST_ID", (Object[]) idsArr));
    }

    /**
     * 根据Flowable GroupQueryImpl查询岗位列表
     *
     * @param query
     * @return
     */
    @Override
    public List<SysPost> getPostsByFlowableGroupQueryImpl(GroupQueryImpl query) {
        return this.baseMapper.getPostsByFlowableGroupQueryImpl(query);
    }
}
