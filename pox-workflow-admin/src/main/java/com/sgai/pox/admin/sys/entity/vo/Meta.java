package com.sgai.pox.admin.sys.entity.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author pox
 * @date 2021年01月04日
 */
@Data
public class Meta implements Serializable {
    private static final long serialVersionUID = 1L;

    private String title;
    private String icon;
    private Boolean isCache = true;
    private Boolean affix;
}
