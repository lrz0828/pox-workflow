package com.sgai.pox.engine.vo;

import lombok.Data;

/**
 * @author pox
 * @date 2020年3月24日
 */
@Data
public class ProcessDefinitionResponse {
    private String id;
    private String key;
    private int version;
    private String name;
    private String description;
    private String tenantId;
    private String category;
    private String formKey;
    private boolean graphicalNotationDefined = false;
    private boolean suspended = false;
}
