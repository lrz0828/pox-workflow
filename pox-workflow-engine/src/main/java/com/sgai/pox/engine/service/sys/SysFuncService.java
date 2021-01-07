package com.sgai.pox.engine.service.sys;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sgai.pox.engine.common.core.base.BaseService;
import com.sgai.pox.engine.entity.sys.SysFunc;

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
