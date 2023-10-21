package com.xoease.gcore;

import org.java_websocket.WebSocket;
import org.java_websocket.drafts.Draft;
import org.java_websocket.exceptions.InvalidDataException;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.handshake.ServerHandshakeBuilder;
import org.java_websocket.server.WebSocketServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import java.io.Serializable;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.util.concurrent.ConcurrentHashMap;

public  class Ser extends WebSocketServer {
    private static final Logger log = LoggerFactory.getLogger(Ser.class);
    protected ConcurrentHashMap<Serializable,Handshake> hans= new ConcurrentHashMap();
    public ApplicationContext context;

    public Ser(int port ) {
        super( new InetSocketAddress( port ) );
    }
    public ServerHandshakeBuilder onWebsocketHandshakeReceivedAsServer(WebSocket conn, Draft draft, ClientHandshake request) throws InvalidDataException {
        ServerHandshakeBuilder builder = super.onWebsocketHandshakeReceivedAsServer( conn, draft, request );
        builder.put( "Access-Control-Allow-Origin" , "*");
        Handshake h=handshake(conn,request);
        conn.setAttachment(h);
        if(!valiToken(conn,request)){
            throw new InvalidDataException(400);
        }
        if(!h.valiToken()){
            throw new InvalidDataException(400);
        }
        hans.put(h.Id(),h);
        return builder;
    }

    public final  int getClientCount(){
            return  hans.size();
    }
    public final Handshake getById(Serializable id){
        return  hans.get(id);
    }

    public Ser(InetSocketAddress address , ApplicationContext app) {
        super( address );
        this.context = app ;
    }

    /**
     *
     * @param conn
     * @param handshake
     * @return 是否通过
     */
    protected boolean valiToken( WebSocket conn, ClientHandshake handshake){
        return true ;
    }
    protected   Handshake handshake(WebSocket conn, ClientHandshake handshake){
        Handshake h =context.getBean(Handshake.class,this, conn, handshake);
        return  h;
    }
    @Override
    public final void onOpen( WebSocket conn, ClientHandshake handshake ) {

        Handshake h=conn.getAttachment();
        h.onOpen();
   }

    @Override
    public void onClose( WebSocket conn, int code, String reason, boolean remote ) {
       Handshake h=  conn.getAttachment();
       hans.remove(h.Id());
       h.onClose(code,reason,remote);
    }

    @Override
    public void onMessage(WebSocket conn, ByteBuffer message) {
        Handshake h=  conn.getAttachment();
        h.onMessage(message);
    }

    @Override
    public void onMessage( WebSocket conn, String message ) {
        Handshake h=  conn.getAttachment();
        h.onMessage(message);
    }
     protected void shutdown() {
        getConnections().forEach( conn ->{
            conn.close();
        });
    }
     @Override
    public  void start(   )   {
        super.start();
         Runtime.getRuntime().addShutdownHook(new Thread() {
             @Override
             public void run() {
                 shutdown();
             }
         });
    }
    @Override
    public void onError( WebSocket conn, Exception ex ) {
        ex.printStackTrace();

        if( conn != null ) {
            ex.printStackTrace();
             // some errors like port binding failed may not be assignable to a specific websocket
        }
    }

    @Override
    public void onStart() {
        log.debug( "sks  started on port: " + this.getPort() );
        setConnectionLostTimeout(100);
       /* MessageOuterClass.Message.Builder me = MessageOuterClass.Message.newBuilder();
    me.setType(MessageOuterClass.MSG.Add_Room_Request);
    me.setData(Any.newBuilder().setValue());*/
    }
 }
