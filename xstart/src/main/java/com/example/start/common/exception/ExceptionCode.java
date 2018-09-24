package com.example.start.common.exception;

/**
 * 异常枚举
 */
public enum ExceptionCode {

    RESOURCE_NOT_EXIT("404", "访问的资源不存在！"),
    SYSTEM_ERROR("0000000", "系统错误，请联系系统管理员!"),
    ILLEGAL_PARAMETER("1000000", "非法参数错误!"),
    ILLEGAL_ACCESS("1000001", "非法访问/没有权限!"),
    FREQUENCY_ERROR("1000002", "请勿重复操作!"),
    UNKNOW_ERROR("1000003", "其它未知错误!"),

    WEBSERVICE_ERROR("1000004", "系统调用外部WEB服务调用错误!"),
    ILLEGAL_VALID_CODE("1000005", "验证码错误!"),
    PASSWORD_ERROR("1000006", "用户名或密码错误!"),
    UN_AUTHORIZED_ERROR("1000007", "请重新登入!"),

    INVALID_OPT("1000008", "无效操作！"),
    DATA_ERROR("1000009", "无效数据!"),
    NOT_DATA("1000010", "未查询到数据!");

    private String code;

    private String message;

    private ExceptionCode(String value, String message) {
        this.code = value;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
