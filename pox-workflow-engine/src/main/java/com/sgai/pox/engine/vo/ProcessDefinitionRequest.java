package com.sgai.pox.engine.vo;

import java.util.Date;

import lombok.Data;

/**
 * @author pox
 * @date 2020年3月24日
 */
@Data
public class ProcessDefinitionRequest {
    private String processDefinitionId;
    private boolean includeProcessInstances = false;
    private Date date;
}
