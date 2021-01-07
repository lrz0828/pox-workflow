package com.sgai.pox.engine.controller;

import java.util.List;

import org.flowable.engine.repository.ProcessDefinition;
import org.flowable.identitylink.api.IdentityLink;
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
import com.sgai.pox.engine.service.ProcessDefinitionService;
import com.sgai.pox.engine.vo.IdentityRequest;

/**
 * @author pox
 * @date 2020年3月24日
 */
@RestController
@RequestMapping("/flowable/processDefinitionIdentityLink")
public class ProcessDefinitionIdentityLinkController extends BaseFlowableController {
    @Autowired
    private ProcessDefinitionService processDefinitionService;

    //@PreAuthorize("@elp.single('flowable:processDefinitionIdentityLink:list')")
    @GetMapping(value = "/list")
    public Result list(@RequestParam String processDefinitionId) {
        ProcessDefinition processDefinition = processDefinitionService.getProcessDefinitionById(processDefinitionId);
        List<IdentityLink> identityLinks =
                repositoryService.getIdentityLinksForProcessDefinition(processDefinition.getId());
        return Result.ok(responseFactory.createIdentityResponseList(identityLinks));
    }

    @Log(value = "新增流程定义授权")
    //@PreAuthorize("@elp.single('flowable:processDefinitionIdentityLink:save')")
    @PostMapping(value = "/save")
    public Result save(@RequestBody IdentityRequest identityRequest) {
        processDefinitionService.saveProcessDefinitionIdentityLink(identityRequest);
        return Result.ok();
    }

    @Log(value = "删除流程定义授权")
    //@PreAuthorize("@elp.single('flowable:processDefinitionIdentityLink:delete')")
    @DeleteMapping(value = "/delete")
    public Result delete(@RequestParam String processDefinitionId, @RequestParam String identityId,
                         @RequestParam String identityType) {
        processDefinitionService.deleteProcessDefinitionIdentityLink(processDefinitionId, identityId, identityType);
        return Result.ok();
    }
}
