package com.cbposter.utils.exception;

import java.io.Serializable;

/**
 * @Auther: lc
 * @Date: 2020/1/8 11:21
 */
public class RException extends RuntimeException implements Serializable {

    public static final long serialVersionUID = 1L;

    private String msg;

    private int code=500;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public RException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public RException(String msg, int code) {
        super(msg);
        this.msg = msg;
        this.code = code;
    }

    public RException(String msg, Throwable e) {
        super(msg, e);
        this.msg = msg;
    }

    public RException(String msg, int code, Throwable e) {
        super(msg, e);
        this.msg = msg;
        this.code = code;
    }
}

