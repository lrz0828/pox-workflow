package com.sgai.pox.engine.vo;

import lombok.Data;

import java.util.Date;

/**
 * @author pox
 * @date 2021年01月04日
 */
@Data
public class TaskUpdateRequest {
    private String id;
    private String name;
    private String assignee;
    private String owner;
    private Date dueDate;
    private String category;
    private String description;
    private int priority;
}
