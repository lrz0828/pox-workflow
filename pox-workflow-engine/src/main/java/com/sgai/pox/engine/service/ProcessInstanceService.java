package com.sgai.pox.engine.service;

import com.sgai.pox.engine.vo.ProcessInstanceRequest;
import com.sgai.pox.engine.vo.query.ProcessInstanceQueryVo;
import org.flowable.engine.history.HistoricProcessInstance;
import org.flowable.engine.runtime.ProcessInstance;

import java.util.List;

/**
 * @author pox
 * @date 2021年01月04日
 */
public interface ProcessInstanceService {
    /**
     * 查询单一流程实例
     *
     * @param processInstanceId
     * @return
     */
    ProcessInstance getProcessInstanceById(String processInstanceId);

    /**
     * 查询单一历史流程实例
     *
     * @param processInstanceId
     * @return
     */
    HistoricProcessInstance getHistoricProcessInstanceById(String processInstanceId);

    /**
     * 启动流程实例
     *
     * @param processInstanceRequest
     */
    void start(ProcessInstanceRequest processInstanceRequest);

    /**
     * 删除流程实例
     *
     * @param processInstanceId
     * @param cascade
     * @param deleteReason
     */
    void delete(String processInstanceId, boolean cascade, String deleteReason);

    /**
     * 激活流程实例
     *
     * @param processInstanceId
     */
    void activate(String processInstanceId);

    /**
     * 挂起流程实例
     *
     * @param processInstanceId
     */
    void suspend(String processInstanceId);

    /**
     * 查询我的流程汇总信息
     *
     * @param processInstanceQueryVo
     */
    List listMyInvolvedSummary(ProcessInstanceQueryVo processInstanceQueryVo);
}
