package com.example.start.common.exception;

/**
 * 页面异常
 */
public class PageException extends Exception {
    private final String code;

    public PageException(String msg, Exception e) {
        super(msg, e);
        this.code = ExceptionCode.UNKNOW_ERROR.getCode();
    }

    public PageException(String code, String message) {
        super(message);
        this.code = code;

    }

    public PageException(String code, String message, Exception e) {
        super(message, e);
        this.code = code;
    }

    public PageException(ExceptionCode exceptionCode) {
        super(exceptionCode.getMessage());
        this.code = exceptionCode.getCode();
    }

    public PageException(ExceptionCode exceptionCode, Exception e) {
        super(exceptionCode.getMessage(), e);
        this.code = exceptionCode.getCode();
    }

    public String getCode() {
        return code;
    }
}
