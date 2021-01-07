package com.sgai.pox.admin.sys.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sgai.pox.admin.sys.entity.SysFunc;

/**
 * 功能Mapper
 *
 * @author pox
 */
public interface SysFuncMapper extends BaseMapper<SysFunc> {
    /**
     * 查询功能列表
     *
     * @param page
     * @param entity
     * @return
     */
    public List<SysFunc> list(IPage<SysFunc> page, @Param("entity") SysFunc entity);
}
