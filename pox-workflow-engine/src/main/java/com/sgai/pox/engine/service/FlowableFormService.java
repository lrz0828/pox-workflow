package com.sgai.pox.engine.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sgai.pox.engine.core.base.BaseService;
import com.sgai.pox.engine.entity.FlowableForm;

/**
 * 流程表单Service
 *
 * @author pox
 */
public interface FlowableFormService extends BaseService<FlowableForm> {
    /**
     * 分页查询流程表单
     *
     * @param page
     * @param flowableForm
     * @return
     */
    IPage<FlowableForm> list(IPage<FlowableForm> page, FlowableForm flowableForm);
}
