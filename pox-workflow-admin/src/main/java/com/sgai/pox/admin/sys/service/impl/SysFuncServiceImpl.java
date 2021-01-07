package com.sgai.pox.admin.sys.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sgai.pox.engine.common.core.base.BaseServiceImpl;
import com.sgai.pox.admin.sys.entity.SysFunc;
import com.sgai.pox.admin.sys.mapper.SysFuncMapper;
import com.sgai.pox.admin.sys.service.SysFuncService;
import org.springframework.stereotype.Service;

/**
 * 功能Service
 *
 * @author pox
 */
@Service
public class SysFuncServiceImpl extends BaseServiceImpl<SysFuncMapper, SysFunc> implements SysFuncService {
    @Override
    public IPage<SysFunc> list(IPage<SysFunc> page, SysFunc sysFunc) {
        return page.setRecords(baseMapper.list(page, sysFunc));
    }
}
