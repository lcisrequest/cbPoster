package com.cbposter.utils;

import org.apache.http.HttpStatus;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: lc
 * @Date: 2020/1/8 11:16
 */
public class Result extends HashMap<String, Object> implements Serializable {
    private static final long serialVersionUID = 1L;

    public Result() {
        put("code", 200);
    }

    public static Result error() {
        return error(HttpStatus.SC_INTERNAL_SERVER_ERROR, "网络异常，请联系管理员");
    }

    public static Result error(String msg) {
        return error(HttpStatus.SC_INTERNAL_SERVER_ERROR, msg);
    }

    public static Result error(int code, String msg) {
        Result r = new Result();
        r.put("code", code);
        r.put("msg", msg);
        return r;
    }

    public static Result ok(String msg) {
        Result r = new Result();
        r.put("msg", msg);
        return r;
    }

    public static Result ok(Map<String, Object> map) {
        Result r = new Result();
        r.putAll(map);
        return r;
    }

    public static Result ok() {
        return new Result();
    }

    public Result put(String key, Object value) {
        super.put(key, value);
        return this;
    }

}

