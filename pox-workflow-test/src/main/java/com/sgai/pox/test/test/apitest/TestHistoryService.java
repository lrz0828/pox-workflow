package com.sgai.pox.test.test.apitest;

import com.sgai.pox.test.config.BaseConfiguation;
import org.flowable.engine.history.HistoricActivityInstance;
import org.flowable.engine.history.HistoricProcessInstance;
import org.flowable.engine.runtime.Execution;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.task.api.history.HistoricTaskInstance;
import org.junit.Test;

import java.util.List;

/**
 * @Auther: lirunze
 * @Date: 2021/1/31 16:52
 * @Description: runtimeService
 */
public class TestHistoryService extends BaseConfiguation {

    // 流程实例历史
    @Test
    public void queryHistoricProcessInstance() {
        String processInstanceId = "";
        HistoricProcessInstance historicProcessInstance = historyService.createHistoricProcessInstanceQuery()
                .processInstanceId(processInstanceId).singleResult();
    }

    // 执行实例历史
    @Test
    public void queryHistoricActivityInstance() {
        String processInstanceId = "";
        List<HistoricActivityInstance> list = historyService.createHistoricActivityInstanceQuery().list();
    }

    // 任务历史
    @Test
    public void queryHistoricTaskInstanceInstance() {
        String processInstanceId = "";
        List<HistoricTaskInstance> list = historyService.createHistoricTaskInstanceQuery().list();
    }
}
