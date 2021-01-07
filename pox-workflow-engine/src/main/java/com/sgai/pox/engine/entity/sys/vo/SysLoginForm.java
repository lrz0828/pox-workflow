package com.sgai.pox.engine.entity.sys.vo;

import lombok.Data;

/**
 * @author pox
 * @date 2020年3月24日
 */
@Data
public class SysLoginForm {
    private String userId;
    private String password;
    private String uuid;
    private String captcha;
}