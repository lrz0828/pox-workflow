package com.sgai.pox.engine.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sgai.pox.engine.core.base.BaseServiceImpl;
import com.sgai.pox.engine.entity.FlowableForm;
import com.sgai.pox.engine.mapper.FlowableFormMapper;
import com.sgai.pox.engine.service.FlowableFormService;
import org.springframework.stereotype.Service;

/**
 * 流程Service
 *
 * @author pox
 */
@Service
public class FlowableFormServiceImpl extends BaseServiceImpl<FlowableFormMapper, FlowableForm> implements FlowableFormService {
    @Override
    public IPage<FlowableForm> list(IPage<FlowableForm> page, FlowableForm flowableForm) {
        return page.setRecords(baseMapper.list(page, flowableForm));
    }
}
