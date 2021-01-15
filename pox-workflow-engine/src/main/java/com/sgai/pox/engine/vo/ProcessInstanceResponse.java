package com.sgai.pox.engine.vo;

import lombok.Data;

import java.util.Date;

/**
 * @author pox
 * @date 2021年01月04日
 */
@Data
public class ProcessInstanceResponse {
    protected String id;
    protected String businessKey;
    protected boolean suspended;
    protected String processDefinitionId;
    protected String processDefinitionName;
    protected String processDefinitionKey;
    protected Integer processDefinitionVersion;
    protected String currentActivityId;
    protected String currentActivityName;
    protected String tenantId;
    protected Date startTime;
    protected String startUserId;
    protected String superProcessInstanceId;
}
