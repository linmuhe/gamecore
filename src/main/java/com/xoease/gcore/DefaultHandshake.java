package com.xoease.gcore;

import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;

/**
 * 业务实现类 每个sock过来都会生成一个 这个 给client
 */
//@Component
//@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class DefaultHandshake extends Handshake implements Reposon  {
    public DefaultHandshake(Ser ser, WebSocket conn, ClientHandshake handshake) {
        this.conn = conn;
        this.handshake = handshake;
        this.server = ser;
     }
    @Override
    public Ac acbean(int code) {
        return (Ac) server.context.getBean(code+"");
    }
    /**
     * 主动断开
     */
    @Override
    public void close() {
        close(110);
    }
}
