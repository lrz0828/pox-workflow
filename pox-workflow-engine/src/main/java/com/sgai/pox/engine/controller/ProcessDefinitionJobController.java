package com.sgai.pox.engine.controller;

import com.sgai.pox.engine.core.base.BaseFlowableController;
import com.sgai.pox.engine.core.base.Result;
import com.sgai.pox.engine.core.log.annotation.Log;
import org.flowable.job.api.Job;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author pox
 * @date 2021年01月04日
 */
@RestController
@RequestMapping("/flowable/processDefinitionJob")
public class ProcessDefinitionJobController extends BaseFlowableController {

    //@PoxPreAuthorize("@elp.single('flowable:processDefinitionJob:list')")
    @GetMapping(value = "/list")
    public List<Job> list(@RequestParam String processDefinitionId) {
        return managementService.createTimerJobQuery().processDefinitionId(processDefinitionId).list();
    }

    @Log(value = "新增流程定义定时任务")
    //@PoxPreAuthorize("@elp.single('flowable:processDefinitionJob:delete')")
    @DeleteMapping(value = "/delete")
    @Transactional(rollbackFor = Exception.class)
    public Result deleteJob(@RequestParam String jobId) {
        managementService.deleteTimerJob(jobId);
        return Result.ok();
    }
}
