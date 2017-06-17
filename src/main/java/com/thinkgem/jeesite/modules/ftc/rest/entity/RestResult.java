package com.thinkgem.jeesite.modules.ftc.rest.entity;

/**
 * Created by bingbing on 2017/6/6.
 */
public class RestResult {
    private String msg;
    private String code;
    private Object data;

    public RestResult(String code, String msg) {
        this(code, msg, null);
    }



    public RestResult(String code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }


    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
