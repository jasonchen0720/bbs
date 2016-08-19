package com.jason.bbs.pojo.vo;

import java.io.Serializable;

/**
 * Created by jason on 2016/8/16.
 */
public class ResponseModel /*implements Serializable*/{

    private int code;

    private String message;

    private Object data;

    public ResponseModel() {

    }

    public ResponseModel(int code) {

        this.code = code;
    }

    public ResponseModel(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public ResponseModel(int code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;

    }

    public static final ResponseModel ok() {
        return new ResponseModel(0);
    }

    public static final ResponseModel error() {
        return new ResponseModel(-1);
    }

    public ResponseModel message(String message) {
        this.message = message;
        return this;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
