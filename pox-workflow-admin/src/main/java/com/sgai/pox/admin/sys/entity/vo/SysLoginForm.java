package com.sgai.pox.admin.sys.entity.vo;

import lombok.Data;

/**
 * @author pox
 * @date 2021年01月04日
 */
@Data
public class SysLoginForm {
    private String userId;
    private String password;
    private String uuid;
    private String captcha;
}