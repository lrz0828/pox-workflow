package com.sgai.pox.admin.job.service.impl;

import com.sgai.pox.admin.job.service.SysJobLogService;
import com.sgai.pox.admin.sys.entity.SysJobLog;
import com.sgai.pox.admin.sys.mapper.SysJobLogMapper;
import com.sgai.pox.engine.common.core.base.BaseServiceImpl;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.metadata.IPage;

/**
 * 定时任务执行日志Service
 *
 * @author pox
 */
@Service
public class SysJobLogServiceImpl extends BaseServiceImpl<SysJobLogMapper, SysJobLog> implements SysJobLogService {
    @Override
    public IPage<SysJobLog> list(IPage<SysJobLog> page, SysJobLog sysJobLog) {
        return page.setRecords(baseMapper.list(page, sysJobLog));
    }
}
