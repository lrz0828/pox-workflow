package com.sgai.pox.admin.sys.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sgai.pox.engine.common.core.base.BaseService;
import com.sgai.pox.admin.sys.entity.SysFunc;

/**
 * 功能Service
 *
 * @author pox
 */
public interface SysFuncService extends BaseService<SysFunc> {
    /**
     * 分页查询功能
     *
     * @param page
     * @param sysFunc
     * @return
     */
    IPage<SysFunc> list(IPage<SysFunc> page, SysFunc sysFunc);
}
