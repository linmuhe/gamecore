package com.xoease.gcore;

import com.google.gson.internal.LinkedTreeMap;

/**
 * game base-function write here
 */
public abstract class BasePro<T extends Reposon,H extends Handshake> implements Ac {
    private H res;
    private   LinkedTreeMap param;

    public  void handler(Object o, T res) {}
    @Override
    public final void a(Object o, ServResp res) {
        this.res = to((T) res);this.res.setAc(this);
        this.param =  (LinkedTreeMap) o;
        this.handler(o,(T) res);
    }
    protected  H getEntity(){
        return res;
    }
    protected  LinkedTreeMap getParams(){
        return  param;
    }
   /* @Override
    protected HandshakeImpl to(Reposon res) {
        return (HandshakeImpl) res;
    }*/
    protected   H to(T res){
        return (H) res;
    }
    /**
     * 主动关闭连接
     * @param res
     */
    protected final void close(T res) {
        res.close();
    }
}
