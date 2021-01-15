package com.sgai.pox.admin.sys.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sgai.pox.admin.sys.entity.SysCodeInfo;

/**
 * 代码信息Mapper
 *
 * @author pox
 */
public interface SysCodeInfoMapper extends BaseMapper<SysCodeInfo> {
    /**
     * 查询代码信息列表
     *
     * @param page
     * @param entity
     * @return
     */
    public List<SysCodeInfo> list(IPage<SysCodeInfo> page, @Param("entity") SysCodeInfo entity);
}
