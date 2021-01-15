package com.sgai.pox.engine.vo;

import lombok.Data;

import java.util.Map;

/**
 * @author pox
 * @date 2021年01月04日
 */
@Data
public class TaskRequest {
    private String taskId;
    private String userId;
    private String message;
    private String activityId;
    private String activityName;
    private Map<String, Object> values;
    private CcToVo[] ccToVos;
    private String[] taskIds;
}
