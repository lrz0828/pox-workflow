package com.sgai.pox.admin.sys.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sgai.pox.admin.sys.entity.SysJob;

/**
 * 定时任务Mapper
 *
 * @author pox
 */
public interface SysJobMapper extends BaseMapper<SysJob> {
    /**
     * 查询定时任务列表
     *
     * @param page
     * @param entity
     * @return
     */
    public List<SysJob> list(IPage<SysJob> page, @Param("entity") SysJob entity);
}
