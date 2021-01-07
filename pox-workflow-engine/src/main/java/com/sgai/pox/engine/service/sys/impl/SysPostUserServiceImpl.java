package com.sgai.pox.engine.service.sys.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sgai.pox.engine.common.core.base.BaseServiceImpl;
import com.sgai.pox.engine.entity.sys.SysPostUser;
import com.sgai.pox.engine.mapper.sys.SysPostUserMapper;
import com.sgai.pox.engine.service.sys.SysPostUserService;
import org.springframework.stereotype.Service;

/**
 * 岗位和用户关系Service
 *
 * @author pox
 */
@Service
public class SysPostUserServiceImpl extends BaseServiceImpl<SysPostUserMapper, SysPostUser> implements SysPostUserService {
    @Override
    public IPage<SysPostUser> list(IPage<SysPostUser> page, SysPostUser sysPostUser) {
        return page.setRecords(baseMapper.list(page, sysPostUser));
    }
}
