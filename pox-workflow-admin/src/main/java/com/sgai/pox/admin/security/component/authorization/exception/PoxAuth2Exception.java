package com.sgai.pox.admin.security.component.authorization.exception;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;

/**
 * @author pox
 */
@JsonSerialize(using = PoxAuth2ExceptionSerializer.class)
public class PoxAuth2Exception extends OAuth2Exception {
    private static final long serialVersionUID = 1L;
    @Getter
    private String errorCode;

    public PoxAuth2Exception(String msg) {
        super(msg);
    }

    public PoxAuth2Exception(String msg, String errorCode) {
        super(msg);
        this.errorCode = errorCode;
    }
}
