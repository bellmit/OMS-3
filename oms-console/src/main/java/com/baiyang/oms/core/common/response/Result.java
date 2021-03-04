package com.baiyang.oms.core.common.response;

import org.springframework.http.HttpStatus;

/**
 * @author  Created by qinghai.peng on 2018/10/8.
 */
public class Result<T> {

    private String  code;

    private String message;

    private T body;


    /**
     * 该构造方法默认status 为200
     */
    public Result() {
        this(HttpStatus.OK.name(), null);
    }

    /**
     * 该构造方法默认status 为200
     * @param body
     */
    public Result(T body) {
        this(HttpStatus.OK.name(), body);
    }

    public Result(String code, T body) {
        this(code, null, body);
    }

    public Result(String code, String message, T body) {
        this.code = code;
        this.body = body;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }
}