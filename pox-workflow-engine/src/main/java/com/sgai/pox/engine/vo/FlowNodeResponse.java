package com.sgai.pox.engine.vo;

import java.util.Date;

import lombok.Data;

/**
 * @author pox
 * @date 2021年01月04日
 */
@Data
public class FlowNodeResponse {
    /**
     * 节点id
     */
    private String nodeId;
    /**
     * 节点名称
     */
    private String nodeName;
    /**
     * 执行人的id
     */
    private String userId;
    /**
     * 执行人姓名
     */
    private String userName;

    /**
     * 任务节点结束时间
     */
    private Date endTime;

}
