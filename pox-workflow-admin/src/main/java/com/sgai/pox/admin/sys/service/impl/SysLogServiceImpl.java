package com.sgai.pox.admin.sys.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sgai.pox.engine.core.base.Result;
import com.sgai.pox.engine.core.base.BaseServiceImpl;
import com.sgai.pox.engine.core.base.LogInfo;
import com.sgai.pox.engine.core.base.LogService;
import com.sgai.pox.admin.sys.entity.SysLog;
import com.sgai.pox.admin.sys.mapper.SysLogMapper;
import com.sgai.pox.admin.sys.service.SysLogService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * 系统日志Service
 *
 * @author pox
 */
@Service
public class SysLogServiceImpl extends BaseServiceImpl<SysLogMapper, SysLog> implements SysLogService, LogService {
    @Override
    public IPage<SysLog> list(IPage<SysLog> page, SysLog sysLog) {
        return page.setRecords(baseMapper.list(page, sysLog));
    }

    @Override
    public Result save(LogInfo logInfo, String inner) {
        SysLog sysLog = new SysLog();
        BeanUtils.copyProperties(logInfo, sysLog);
        save(sysLog);
        return Result.ok();
    }
}
