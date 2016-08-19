package com.jason.bbs.pojo.bo;

/**
 * Created by jason on 2016/8/19.
 */
public class CommonBo {

    private Boolean result;

    private String message;

    public CommonBo(){
    }

    public CommonBo(Boolean result) {
        this.result = result;
    }

    public CommonBo(String message) {
        this.message = message;
    }

    public CommonBo(Boolean result, String message){
        this.result = result;
        this.message = message;
    }

    public static final CommonBo success(){
        return new CommonBo(true);
    }

    public static final CommonBo fail(){
        return new CommonBo(false);
    }

    public CommonBo message(String message){
        this.message = message;
        return this;
    }

    public Boolean isSuccess(){
        return this.result;
    }

    public Boolean getResult() {
        return result;
    }

    public void setResult(Boolean result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
