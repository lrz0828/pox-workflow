package com.sgai.pox.engine.controller;

import java.util.List;

import org.flowable.job.api.Job;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sgai.pox.engine.common.core.Result;
import com.sgai.pox.engine.common.log.annotation.Log;
import com.sgai.pox.engine.common.BaseFlowableController;

/**
 * @author pox
 * @date 2020年3月24日
 */
@RestController
@RequestMapping("/flowable/processDefinitionJob")
public class ProcessDefinitionJobController extends BaseFlowableController {

    //@PreAuthorize("@elp.single('flowable:processDefinitionJob:list')")
    @GetMapping(value = "/list")
    public List<Job> list(@RequestParam String processDefinitionId) {
        return managementService.createTimerJobQuery().processDefinitionId(processDefinitionId).list();
    }

    @Log(value = "新增流程定义定时任务")
    //@PreAuthorize("@elp.single('flowable:processDefinitionJob:delete')")
    @DeleteMapping(value = "/delete")
    @Transactional(rollbackFor = Exception.class)
    public Result deleteJob(@RequestParam String jobId) {
        managementService.deleteTimerJob(jobId);
        return Result.ok();
    }
}
