package com.sgai.pox.admin.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sgai.pox.admin.sys.entity.SysLog;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 系统日志Mapper
 *
 * @author pox
 */
public interface SysLogMapper extends BaseMapper<SysLog> {
    /**
     * 查询系统日志列表
     *
     * @param page
     * @param entity
     * @return
     */
    public List<SysLog> list(IPage<SysLog> page, @Param("entity") SysLog entity);
}
