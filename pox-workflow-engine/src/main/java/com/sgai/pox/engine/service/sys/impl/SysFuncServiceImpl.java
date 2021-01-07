package com.sgai.pox.engine.service.sys.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sgai.pox.engine.common.core.base.BaseServiceImpl;
import com.sgai.pox.engine.entity.sys.SysFunc;
import com.sgai.pox.engine.mapper.sys.SysFuncMapper;
import com.sgai.pox.engine.service.sys.SysFuncService;
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
