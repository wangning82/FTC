package com.thinkgem.jeesite.modules.ftc.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bingbing on 2017/6/6.
 */
public class RestResult {
    public static String SUCCESS="1";
    private String msg;
    private String code;
    private int count;
    private Object data;

    public RestResult(String code,String msg,Object data,int count){
      this.code=code;
        this.msg=msg;
        this.data=data;
        this.count=count;
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

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
