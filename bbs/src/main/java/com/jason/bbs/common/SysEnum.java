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

        USER_REGISTER_OK("用户注册成功"),
        USER_REGISTER_ERROR("用户注册失败"),
        USER_LOGIN_OK("用户登录成功"),
        USER_NOT_FOUND("用户不存在"),
        USER_PASSWORD_ERROR("用户密码错误"),

        ISSUE_LOAD_OK("加载帖子列表成功"),
        ISSUE_LOAD_ERROR("加载帖子列表失败"),
        ISSUE_NOT_FOUND("帖子不存在"),
        ISSUE_GET_OK("获取帖子成功"),
        ISSUE_SAVE_OK("保存帖子成功"),
        ISSUE_SAVE_ERROR("保存帖子失败"),

        COMMENT_SAVE_OK("评论成功"),
        COMMENT_SAVE_ERROR("评论失败"),
        COMMENT_NOT_FOUND("评论不存在或被删除"),

        REPLY_SAVE_OK("回复成功"),
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
