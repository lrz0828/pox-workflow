package com.sgai.pox.engine.vo;

import lombok.Data;

@Data
public class ProcessDefinitionVo {
    private String category;
    private String categoryName;
    private String processDefinitionKey;
    private String processDefinitionName;
    private Long count;
}
