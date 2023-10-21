package com.xoease.gcore;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
 import org.java_websocket.WebSocket;
import org.java_websocket.framing.CloseFrame;
import org.java_websocket.handshake.ClientHandshake;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
 import java.nio.ByteBuffer;

public abstract class Handshake implements Comparable<Handshake>{
    private static final Logger log = LoggerFactory.getLogger(Handshake.class);

    protected Ser server;
        protected WebSocket conn;
        protected ClientHandshake handshake ;

    protected Object  spielEntity ;
    private boolean firstMsg=false;
    protected boolean BINARY_MESSAGE= false;

    public   void onOpen(){

        }
    public   void onClose(   int code, String reason, boolean remote ){
          if(_lstac!=null) _lstac.onClose(  code,  reason,  remote);
    }
    public final <T> T  getEneity(){
        return (T) this.spielEntity;
    }

     private Ac _lstac;

     public void onMessage(String message) {
        try {
            Gson gs = new Gson();
            Pack pack = gs.fromJson(message, Pack.class);
            if(pack!=null){
                acbean(pack.getCode()).a(pack.getData(), (ServResp)this);
                if(!firstMsg){
                    firstMsg = true ;
                    _lstac=getAc();
                    if(_lstac!=null) {
                        this.spielEntity=_lstac.onEnter();
                        if(this.spielEntity==null){
                            log.warn("canot get a spielEntity on enter method ");
                        }
                    }
                }
            }

          /* String[] x= message.split(",");
            System.err.println( Server.spiService.geldPack(Double.parseDouble(x[0]),Integer.parseInt(x[1])));*/
            //  System.err.println(Server.userService.incv("1",1));
            // System.err.println( Server.spiService.getKonfig());
            //
           /* Server.spiService.einSpielRec(null);
            System.err.println( Server.userService.token("xxxx"));*/
        } catch (Exception e) {
            /*
            *
            * 0–999		保留段, 未使用.
1000	CLOSE_NORMAL	正常关闭; 无论为何目的而创建, 该链接都已成功完成任务.
1001	CLOSE_GOING_AWAY	终端离开, 可能因为服务端错误, 也可能因为浏览器正从打开连接的页面跳转离开.
1002	CLOSE_PROTOCOL_ERROR	由于协议错误而中断连接.
1003	CLOSE_UNSUPPORTED	由于接收到不允许的数据类型而断开连接 (如仅接收文本数据的终端接收到了二进制数据).
1004		保留. 其意义可能会在未来定义.
1005	CLOSE_NO_STATUS	保留. 表示没有收到预期的状态码.
1006	CLOSE_ABNORMAL	保留. 用于期望收到状态码时连接非正常关闭 (也就是说, 没有发送关闭帧).
1007	Unsupported Data	由于收到了格式不符的数据而断开连接 (如文本消息中包含了非 UTF-8 数据).
1008	Policy Violation	由于收到不符合约定的数据而断开连接. 这是一个通用状态码, 用于不适合使用 1003 和 1009 状态码的场景.
1009	CLOSE_TOO_LARGE	由于收到过大的数据帧而断开连接.
1010	Missing Extension	客户端期望服务器商定一个或多个拓展, 但服务器没有处理, 因此客户端断开连接.
1011	Internal Error	客户端由于遇到没有预料的情况阻止其完成请求, 因此服务端断开连接.
1012	Service Restart	服务器由于重启而断开连接.
1013	Try Again Later	服务器由于临时原因断开连接, 如服务器过载因此断开一部分客户端连接.
1014		由 WebSocket标准保留以便未来使用.
1015	TLS Handshake	保留. 表示连接由于无法完成 TLS 握手而关闭 (例如无法验证服务器证书).
1016–1999		由 WebSocket标准保留以便未来使用.
2000–2999		由 WebSocket拓展保留使用.
3000–3999		可以由库或框架使用.? 不应由应用使用. 可以在 IANA 注册, 先到先得.
4000–4999		可以由应用使用.
            * */
            this.conn.close(CloseFrame.REFUSE);
            e.printStackTrace();
        }
    }
    public final void onMessage(ByteBuffer message){
        if(!this.BINARY_MESSAGE){
            String ss = new String(message.array());
            this.onMessage(ss);
        }else{
            String ss = this.deco(message);
            this.onMessage(ss);
        }
    }
    protected ByteBuffer enco(String json){
        try {
            byte[] c = json.getBytes("UTF-8");
            ByteBuffer b = ByteBuffer.allocate(2+c.length);
            b.putShort((short) json.length());
            ByteBuffer d = ByteBuffer.wrap(c);
            b.put(d);b.flip();
             return b ;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
    protected String deco(ByteBuffer message){
        short len = message.getShort();
        byte[] dst= new byte[len];
        ByteBuffer buf = message.get(dst);
        try {
            if ( buf.remaining() <  len) {
                throw new RuntimeException("no enough data");
            }
            return new String(dst,"UTF-16LE");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void close(int code){
        this.conn.close(code);
    }
    public void send(Pack pack){
        Gson gs = serileGson();
        if(!this.BINARY_MESSAGE){
            this.conn.send(gs.toJson(pack));
        }else{
            ByteBuffer v = this.enco(gs.toJson(pack));
            this.conn.send(v);
        }
    }
    protected Gson serileGson() {
        return new GsonBuilder().serializeNulls()
                //.excludeFieldsWithoutExposeAnnotation()
                .create();
    }
    @Override
    public int compareTo(Handshake o) {
        if(this.conn!=null && o.conn!=null && this.conn!=o.conn) return  1  ;else if (this.conn==o.conn){
             return  0;
        }else{
            return  -1 ;
        }
    }
    public abstract Ac acbean(int code);

    protected boolean valiToken(){
        return true ;
    }
    public Serializable Id(){
        return conn.toString();
    }
    public void setAc(Ac ac){
       th.set(ac);
    }
    protected Ac getAc(){
         return  th.get();
    }
    private ThreadLocal<Ac> th = new ThreadLocal();

}