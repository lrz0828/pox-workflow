package com.sgai.pox.engine.common.core.exception;

/**
 * @author pox
 * @date 2021年01月04日
 */
public class AppException extends BaseException {

    private static final long serialVersionUID = 1L;

    public AppException() {
    }

    public AppException(Throwable cause) {
        super(cause);
    }

    public AppException(String errMsg) {
        super(errMsg);
    }

    public AppException(String errMsg, Throwable cause) {
        super(errMsg, cause);
    }

    public AppException(String errCode, String errMsg, Throwable cause) {
        super(errMsg, cause);
        this.errCode = errCode;
    }
}
