package com.jason.bbs.error;

/**
 * Created by jason on 2016/8/15.
 */
public class BaseSystemException extends RuntimeException {

    private String errorMessage;

    public BaseSystemException(BbsErrorEnum error) {
        this.errorMessage = error.getMessage();
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}