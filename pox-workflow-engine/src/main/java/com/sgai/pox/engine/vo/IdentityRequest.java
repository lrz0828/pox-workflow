package com.sgai.pox.engine.vo;

import lombok.Data;

/**
 * @author pox
 * @date 2020年3月24日
 */
@Data
public class IdentityRequest {
    private String processDefinitionId;
    private String taskId;
    private String identityId;
    private String identityType;
}
