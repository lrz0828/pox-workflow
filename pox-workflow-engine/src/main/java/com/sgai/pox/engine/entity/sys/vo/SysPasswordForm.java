package com.sgai.pox.engine.entity.sys.vo;

import lombok.Data;

/**
 * @author pox
 * @date 2020年3月24日
 */
@Data
public class SysPasswordForm {
    /**
     * 原密码
     */
    private String password;
    /**
     * 新密码
     */
    private String newPassword;

}
