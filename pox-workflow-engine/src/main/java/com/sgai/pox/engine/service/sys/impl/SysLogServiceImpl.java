package com.sgai.pox.engine.service.sys.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sgai.pox.engine.common.core.Result;
import com.sgai.pox.engine.common.core.base.BaseServiceImpl;
import com.sgai.pox.engine.common.core.base.LogInfo;
import com.sgai.pox.engine.common.core.base.LogService;
import com.sgai.pox.engine.entity.sys.SysLog;
import com.sgai.pox.engine.mapper.sys.SysLogMapper;
import com.sgai.pox.engine.service.sys.SysLogService;
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
