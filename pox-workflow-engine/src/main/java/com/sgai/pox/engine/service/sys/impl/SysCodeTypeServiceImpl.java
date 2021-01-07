package com.sgai.pox.engine.service.sys.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sgai.pox.engine.common.core.base.BaseServiceImpl;
import com.sgai.pox.engine.common.core.constant.CacheConstants;
import com.sgai.pox.engine.common.core.exception.SysException;
import com.sgai.pox.engine.common.redis.util.RedisUtil;
import com.sgai.pox.engine.entity.sys.SysCodeInfo;
import com.sgai.pox.engine.entity.sys.SysCodeType;
import com.sgai.pox.engine.mapper.sys.SysCodeTypeMapper;
import com.sgai.pox.engine.service.sys.SysCodeInfoService;
import com.sgai.pox.engine.service.sys.SysCodeTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

/**
 * 代码类别Service
 *
 * @author pox
 */
@Service
public class SysCodeTypeServiceImpl extends BaseServiceImpl<SysCodeTypeMapper, SysCodeType> implements SysCodeTypeService {

    @Autowired
    private SysCodeInfoService sysCodeInfoService;

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public IPage<SysCodeType> list(IPage<SysCodeType> page, SysCodeType sysCodeType) {
        return page.setRecords(baseMapper.list(page, sysCodeType));
    }

    /**
     * 删除数据字典信息
     *
     * @param ids
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteSysCodeType(String ids) {
        if (ids == null || ids.trim().length() == 0) {
            throw new SysException("ids can't be empty");
        }
        String[] idsArr = ids.split(",");
        if (idsArr.length > 1) {
            removeByIds(Arrays.asList(idsArr));
        } else {
            removeById(idsArr[0]);
        }
        sysCodeInfoService.remove(new QueryWrapper<SysCodeInfo>().in("code_type_id", (Object[]) idsArr));
        for (String codeTypeId : idsArr) {
            redisUtil.del(CacheConstants.SYS_CODE_TYPE + codeTypeId);
        }
    }
}
