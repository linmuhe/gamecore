package com.xoease.gcore.example;


import com.xoease.gcore.Handshake;
import com.xoease.gcore.Ser;
import com.xoease.gcore.example.Action.ServerIface;
import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.Cache;
import org.springframework.context.ApplicationContext;

import java.net.InetSocketAddress;


public class Server extends Ser implements ServerIface {

    public static Cache cache ;

    public Server(int port ,ApplicationContext app) {
        this( new InetSocketAddress( port ),app );
    }

    public Server(InetSocketAddress addr, ApplicationContext app) {
        super(addr,app);
     }
    protected static final Logger log = LoggerFactory.getLogger(Server.class);


    /*@Override
    public void onClose(WebSocket conn, int code, String reason, boolean remote) {
         super.onClose(conn, code, reason, remote);
    }*/
    @Override
    protected Handshake handshake(WebSocket conn, ClientHandshake handshake) {
        cache = context.getBean(Cache.class);
        return  super.handshake(conn,handshake);
    }

    /**
     *
     * @param conn
     * @param handshake
     * @return
     */
    @Override
    protected boolean valiToken(WebSocket conn, ClientHandshake handshake) {
        String token = handshake.getFieldValue("token") ;
        try {
            HandshakeImpl h=conn.getAttachment();
            //验证 用户
            return  true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


}
