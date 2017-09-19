package com.vincyan.ncextensions.bean;

/**
 * Created by Shinelon on 2016/12/14.
 */

public class Bean {

    private String msg;

    public Bean(String msg){
        this.msg=msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "Bean{" +
                "msg='" + msg + '\'' +
                '}';
    }
}
