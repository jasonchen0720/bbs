package com.jason.bbs.common;

/**
 * Created by jason on 2016/8/10.
 */
public class SysEnum {

    public enum ResultCode {

        ERROR("-1"),OK("0");

        private String code;

        private ResultCode(String code){
            this.code = code;
        }

        public String getCode() {
            return code;
        }
    }

    public enum ResultMsg{

        USER_NOT_FOUND("用户不存在"),

        USER_PASSWORD_ERROR("用户密码错误"),

        ISSUE_NOT_FOUND("帖子不存在"),

        COMMENT_SAVE_ERROR("评论失败"),

        COMMENT_NOT_FOUND("评论不存在或被删除"),

        REPLY_SAVE_EEROR("回复失败");



        private String msg;

        private ResultMsg(String msg){
            this.msg = msg;
        }

        public String getMsg() {
            return msg;
        }

    }


}
