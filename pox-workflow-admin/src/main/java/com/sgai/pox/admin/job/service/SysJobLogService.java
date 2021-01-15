package com.sgai.pox.admin.job.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sgai.pox.admin.sys.entity.SysJobLog;
import com.sgai.pox.engine.core.base.BaseService;

/**
 * 定时任务执行日志Service
 *
 * @author pox
 */
public interface SysJobLogService extends BaseService<SysJobLog> {
    /**
     * 分页查询定时任务执行日志
     *
     * @param page
     * @param sysJobLog
     * @return
     */
    IPage<SysJobLog> list(IPage<SysJobLog> page, SysJobLog sysJobLog);
}
