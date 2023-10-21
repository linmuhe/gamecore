package com.xoease.gcore.example.Action;


import com.xoease.gcore.example.HandshakeImpl;

/**
 * socket handle common function write here
 */
public interface Reposon extends com.xoease.gcore.Reposon,ServerIface {

    default ServerIface getSer(){
        if(this instanceof HandshakeImpl){
            return  ((HandshakeImpl)this).ser();
        }
        return null;
    }


}
