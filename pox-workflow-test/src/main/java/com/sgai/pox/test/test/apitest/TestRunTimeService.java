package com.sgai.pox.test.test.apitest;

import com.sgai.pox.test.config.BaseConfiguation;
import org.flowable.common.engine.impl.identity.Authentication;
import org.flowable.engine.runtime.Execution;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.engine.runtime.ProcessInstanceQuery;
import org.flowable.task.api.TaskQuery;
import org.junit.Test;

import java.util.List;

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
}
