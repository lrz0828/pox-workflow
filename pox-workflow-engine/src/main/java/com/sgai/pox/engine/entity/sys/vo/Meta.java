package com.sgai.pox.engine.entity.sys.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author pox
 * @date 2020年3月24日
 */
@Data
public class Meta implements Serializable {
    private static final long serialVersionUID = 1L;

    private String title;
    private String icon;
    private Boolean isCache = true;
    private Boolean affix;
}
