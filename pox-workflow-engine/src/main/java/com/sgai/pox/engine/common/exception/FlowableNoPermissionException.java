package com.sgai.pox.engine.common.exception;

import org.flowable.common.engine.api.FlowableException;

/**
 * @author pox
 * @date 2021年01月04日
 */
public class FlowableNoPermissionException extends FlowableException {

    private static final long serialVersionUID = 1L;

    public FlowableNoPermissionException(String message) {
        super(message);
    }

    public FlowableNoPermissionException(String message, Throwable cause) {
        super(message, cause);
    }
}
