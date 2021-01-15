package com.sgai.pox.engine.vo;

import lombok.Data;

import java.util.List;

@Data
public class CategoryVo {
    private String category;
    private String categoryName;
    private List<ProcessDefinitionVo> processDefinitionVoList;
}
