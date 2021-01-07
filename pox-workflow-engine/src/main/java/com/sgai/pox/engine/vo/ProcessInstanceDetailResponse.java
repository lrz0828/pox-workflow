package com.sgai.pox.engine.vo;

import lombok.Data;

/**
 * @author pox
 * @date 2020年3月24日
 */
@Data
public class ProcessInstanceDetailResponse extends HistoricProcessInstanceResponse {
    private boolean suspended;
    private String deleteReason;
    private String startUserName;

}
