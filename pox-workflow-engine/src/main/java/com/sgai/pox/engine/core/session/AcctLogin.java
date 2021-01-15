package com.sgai.pox.engine.core.session;

import lombok.Data;

/**
 * @Auther: pox
 */
@Data
public class AcctLogin {

    //用户账号
    private String userName;

    //用户密码
    private String password;

    private String sessionId;

    private String clientType;

}
