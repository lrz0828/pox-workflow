package com.sgai.pox.admin.sys.entity.vo;

import lombok.Data;

/**
 * @author pox
 * @date 2021年01月04日
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
