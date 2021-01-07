package com.sgai.pox.engine.controller;

import java.util.List;

import org.flowable.identitylink.api.history.HistoricIdentityLink;
import org.flowable.task.api.history.HistoricTaskInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sgai.pox.engine.common.core.Result;
import com.sgai.pox.engine.common.log.annotation.Log;
import com.sgai.pox.engine.common.BaseFlowableController;
import com.sgai.pox.engine.service.FlowableTaskService;
import com.sgai.pox.engine.vo.IdentityRequest;

/**
 * @author pox
 * @date 2020年3月24日
 */
@RestController
@RequestMapping("/flowable/taskIdentityLink")
public class TaskIdentityLinkController extends BaseFlowableController {
    @Autowired
    protected FlowableTaskService flowableTaskService;

    //@PreAuthorize("@elp.single('flowable:taskIdentityLink:list')")
    @GetMapping(value = "/list")
    public Result list(@RequestParam String taskId) {
        HistoricTaskInstance task = flowableTaskService.getHistoricTaskInstanceNotNull(taskId);
        List<HistoricIdentityLink> historicIdentityLinks = historyService.getHistoricIdentityLinksForTask(task.getId());
        return Result.ok(responseFactory.createTaskIdentityResponseList(historicIdentityLinks));
    }

    @Log(value = "新增任务授权")
    //@PreAuthorize("@elp.single('flowable:taskIdentityLink:save')")
    @PostMapping(value = "/save")
    public Result save(@RequestBody IdentityRequest taskIdentityRequest) {
        flowableTaskService.saveTaskIdentityLink(taskIdentityRequest);
        return Result.ok();
    }

    @Log(value = "删除任务授权")
    //@PreAuthorize("@elp.single('flowable:taskIdentityLink:delete')")
    @DeleteMapping(value = "/delete")
    public Result deleteIdentityLink(@RequestParam String taskId, @RequestParam String identityId,
                                     @RequestParam String identityType) {
        flowableTaskService.deleteTaskIdentityLink(taskId, identityId, identityType);
        return Result.ok();
    }
}
