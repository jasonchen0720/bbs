package com.jason.bbs.error;

/**
 * Created by jason on 2016/8/15.
 */
public enum BbsErrorEnum {

    BBS_PARAM_NULL("0000", "参数为空"),
    BBS_EMAIL_NULL("0001", "邮箱为空"),
    BBS_NAME_NULL("0002", "昵称为空"),
    BBS_EMAIL_EXISTED("0003", "邮箱已被注册"),
    BBS_NAME_EXISTED("0004", "昵称已被注册"),

    SYS_EXCEPTION("10000", "系统错误");

    private String code;
    private String message;

    BbsErrorEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
