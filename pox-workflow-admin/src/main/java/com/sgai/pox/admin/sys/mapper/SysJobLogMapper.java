package com.sgai.pox.admin.sys.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sgai.pox.admin.sys.entity.SysJobLog;

/**
 * 定时任务执行日志Mapper
 *
 * @author pox
 */
public interface SysJobLogMapper extends BaseMapper<SysJobLog> {
    /**
     * 查询定时任务执行日志列表
     *
     * @param page
     * @param entity
     * @return
     */
    public List<SysJobLog> list(IPage<SysJobLog> page, @Param("entity") SysJobLog entity);
}
