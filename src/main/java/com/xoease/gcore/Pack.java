package com.xoease.gcore;

import lombok.Data;

@Data
public class Pack   {
    private Object data;
    private Integer code =0;
    public static   Pack  inst(Object data){
        Pack  x = new Pack ();
        x.data = data;
        return  x;
    }


    public Pack setCode(Integer code) {
        this.code = code;
        return this;
    }

    public Pack() {
    }

    public Pack(Integer code,Object data) {
        this.data = data;
        this.code = code;
    }
}
