package com.sgai.pox.engine.service.sys;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sgai.pox.engine.common.core.base.BaseService;
import com.sgai.pox.engine.entity.sys.SysLog;

/**
 * 系统日志Service
 *
 * @author pox
 */
public interface SysLogService extends BaseService<SysLog> {
    /**
     * 分页查询系统日志
     *
     * @param page
     * @param sysLog
     * @return
     */
    IPage<SysLog> list(IPage<SysLog> page, SysLog sysLog);
}
