package com.sgai.pox.engine.vo;

import java.util.Date;

import lombok.Data;

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
