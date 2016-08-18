package com.jason.bbs.pojo.vo;

import com.alibaba.fastjson.annotation.JSONField;
import org.springframework.validation.BindingResult;

import java.io.Serializable;

/**
 * Created by jason on 2016/8/15.
 */
public class AjaxResponse implements Serializable {

    /**
     * 成功:0
     *
     * @return
     */
    public static final AjaxResponse ok() {
        return new AjaxResponse(0);
    }

    /**
     * 失败:1
     *
     * @return
     */
    public static final AjaxResponse fail() {
        return new AjaxResponse(1);
    }

    /**
     * 自定义错误码
     *
     * @param code
     * @return
     */
    public static final AjaxResponse fail(int code) {
        return new AjaxResponse(code);
    }

    /**
     * 无数据:2
     *
     * @return
     */
    public static final AjaxResponse noData() {
        return new AjaxResponse(2).msg("无数据'");
    }

    /**
     * 表单验证失败:100
     *
     * @return
     */
    public static final AjaxResponse validationFail() {
        return new AjaxResponse(100);
    }

    /**
     * 服务不可用:500
     *
     * @return
     */
    public static final AjaxResponse unavailable() {
        return new AjaxResponse(500);
    }

    @JSONField(ordinal = 1, name = "code")
    private int code;

    @JSONField(ordinal = 2, name = "msg")
    private String msg;

    @JSONField(ordinal = 3, name = "data")
    protected Object data;

    @JSONField(ordinal = 4, name = "callback", serialize = false)
    protected String callback;

    private AjaxResponse(int code) {
        this.code = code;
    }

    public AjaxResponse msg(String msg) {
        this.msg = msg;
        return this;
    }

    public AjaxResponse data(Object data) {
        this.data = data;
        return this;
    }

    public AjaxResponse callback(String callback) {
        this.callback = callback;
        return this;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getCallback() {
        return callback;
    }

    public void setCallback(String callback) {
        this.callback = callback;
    }

    /**
     * response data
     *
     * @param bindingResult
     * @return
     */
    public AjaxResponse tips(BindingResult bindingResult) {
        throw new UnsupportedOperationException("unsupported operation");
    }
}