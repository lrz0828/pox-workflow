package com.sgai.pox.admin.sys.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sgai.pox.engine.core.base.BaseServiceImpl;
import com.sgai.pox.admin.sys.entity.SysPostUser;
import com.sgai.pox.admin.sys.mapper.SysPostUserMapper;
import com.sgai.pox.admin.sys.service.SysPostUserService;
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
