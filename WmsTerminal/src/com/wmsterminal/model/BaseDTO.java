package com.wmsterminal.model;

import java.io.Serializable;

/**
 * @author shuhaiyang
 * @date 2016/3/12 16:11.
 */
public class BaseDTO implements Serializable {
    private int code;
    private String message;

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
}
