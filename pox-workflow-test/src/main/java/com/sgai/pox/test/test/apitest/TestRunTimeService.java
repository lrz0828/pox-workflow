package com.sgai.pox.test.test.apitest;

import com.sgai.pox.test.config.BaseConfiguation;
import org.flowable.common.engine.impl.identity.Authentication;
import org.flowable.engine.ProcessEngineConfiguration;
import org.flowable.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.flowable.engine.impl.persistence.entity.ExecutionEntityManager;
import org.flowable.engine.runtime.DataObject;
import org.flowable.engine.runtime.Execution;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.engine.runtime.ProcessInstanceQuery;
import org.flowable.task.api.TaskQuery;
import org.junit.Test;

import java.util.List;
import java.util.Map;

/**
 * @Auther: lirunze
 * @Date: 2021/1/31 16:52
 * @Description: runtimeService
 */
public class TestRunTimeService extends BaseConfiguation {

    // 启动流程实例
    @Test
    public void startProcessInstanceById() {
        String authenticatedUserId = "";
        // 设置启动人
//        identityService.setAuthenticatedUserId(authenticatedUserId);
        Authentication.setAuthenticatedUserId(authenticatedUserId);
        ProcessInstance subprocesstest = runtimeService.startProcessInstanceByKey("subprocesstest");
    }

    // 查询
    @Test
    public void queryMyTask() {
        String taskAssignee = "";
        String processDefinitionKey = "";
        taskService.createTaskQuery().taskAssignee(taskAssignee)
                .processDefinitionKey(processDefinitionKey).list();
    }

    /**
     * 完成任务 完成后有后续流程会加一条数据，没有则结束流程
     */
    @Test
    public void complete() {
        String taskId = "";
        taskService.complete(taskId);
    }


    /**
     * 查看流程是否结束
     */
    @Test
    public void queryProcessInstanceStatus() {
        String processInstanceId = "";
        ProcessInstance processInstance = runtimeService.createProcessInstanceQuery()
                .processInstanceId(processInstanceId).singleResult();
    }

    /**
     * 查看执行实例
     */
    @Test
    public void queryExecution() {
        List<Execution> list = runtimeService.createExecutionQuery().list();
    }

    /**
     * DataObject
     */
    @Test
    public void getDataObject() {
        String executionId = "";
        String dataObject = "";
        DataObject object = runtimeService.getDataObject(executionId, dataObject);
        Map<String, DataObject> dataObjects = runtimeService.getDataObjects(executionId);
    }

    /**
     * 删除流程实例
     */
    @Test
    public void deleteProcessInstance() {
        String processInstanceId = "";
        String deleteReason = "";
        runtimeService.deleteProcessInstance(processInstanceId, deleteReason);
    }

    /**
     * 运行的活动节点
     */
    @Test
    public void getActiveActivityIds() {
        String executionId = "";
        List<String> activeActivityIds = runtimeService.getActiveActivityIds(executionId);
    }
}
