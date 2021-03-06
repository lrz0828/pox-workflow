package com.sgai.pox.engine.vo;

import lombok.Data;

import java.util.Map;

/**
 * @author pox
 * @date 2021年01月04日
 */
@Data
public class ProcessInstanceRequest {
    private String processDefinitionId;
    private String processDefinitionKey;
    private String tenantId;
    private String businessKey;
    private Map<String, Object> values;
    private String processInstanceId;
    private CcToVo[] ccToVos;
}
