package com.example.start.common.exception;

/**
 * 自定义服务异常
 */
public class ServiceException extends RuntimeException {

    private final String code;

    public ServiceException(String msg, Exception e) {
        super(msg, e);
        this.code = ExceptionCode.UNKNOW_ERROR.getCode();
    }

    public ServiceException(String code, String message) {
        super(message);
        this.code = code;

    }

    public ServiceException(String code, String message, Exception e) {
        super(message, e);
        this.code = code;
    }

    public ServiceException(ExceptionCode exceptionCode) {
        super(exceptionCode.getMessage());
        this.code = exceptionCode.getCode();
    }

    public ServiceException(ExceptionCode exceptionCode, Exception e) {
        super(exceptionCode.getMessage(), e);
        this.code = exceptionCode.getCode();
    }

    public String getCode() {
        return code;
    }
}