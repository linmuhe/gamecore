package com.xoease.gcore.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.xoease.gcore.Ac;
import com.xoease.gcore.DefaultHandshake;
import com.xoease.gcore.Pack;
import com.xoease.gcore.Ser;
import com.xoease.gcore.example.Action.Reposon;
import com.xoease.gcore.example.Action.ReqAc;
import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;


/**
 * 业务实现类
 */
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class HandshakeImpl extends DefaultHandshake implements Reposon {
    private String groupid;

    private Map<String, String> userinfo;
    private String utoken;

    public Map<String, String> getUserinfo() {
        return userinfo;
    }

    public HandshakeImpl(Ser ser, WebSocket conn, ClientHandshake handshake) {
        super(ser, conn, handshake);
    }

    /**
     *   send servinfo
     */
    @Override
    public void onOpen() {
        try {
            super.onOpen();
            sendInfo();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public void sendInfo() throws Exception {

        send(new Pack(ReqAc.RES_UINFO,this.userinfo));
        send(new Pack(ReqAc.RES_SINFO,"xxx"));
    }

    public final  Server ser(){
        return (Server) this.server;
    }
    @Override
    public void onClose(int code, String reason, boolean remote) {
        try {
            super.onClose(code,reason,remote);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }






    @Override
    public void close(int code){
        //客户断开操作
        this.conn.close(code);
    }

  @Override
    public void send(Pack pack){
        Gson gs = serileGson();
        this.conn.send(gs.toJson(pack));
    }


    @Override
    public void close() {
        close(110);
    }

     protected Gson serileGson() {
         return new GsonBuilder().serializeNulls()
                    .create();
    }


    public   void setUserinfo(Map<String, String> b){
        this.userinfo = b ;
    }


}
