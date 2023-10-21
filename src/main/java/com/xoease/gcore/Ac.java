package com.xoease.gcore;

public interface Ac {
    default void a(Object o, ServResp res){}

   // default void onOpen(){}

    default void onClose(int code, String reason, boolean remote){}

    default  Object onEnter(){
        return null;
    }
}
