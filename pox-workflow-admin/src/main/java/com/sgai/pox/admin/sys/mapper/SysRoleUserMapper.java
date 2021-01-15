package com.sgai.pox.admin.sys.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sgai.pox.admin.sys.entity.SysRoleUser;

/**
 * 角色和用户关系Mapper
 *
 * @author pox
 */
public interface SysRoleUserMapper extends BaseMapper<SysRoleUser> {
    /**
     * 查询角色和用户关系列表
     *
     * @param page
     * @param entity
     * @return
     */
    public List<SysRoleUser> list(IPage<SysRoleUser> page, @Param("entity") SysRoleUser entity);
}
