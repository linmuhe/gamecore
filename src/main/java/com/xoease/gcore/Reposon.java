package com.xoease.gcore;

/**
 * socket handle common function write here
 */
public interface Reposon extends ServResp {


    void close(int code);
    /**
     * 发送数据
     * @param pack
     */
    void send(Pack pack);

    /**
     * 主动关闭
     */
    void close();
}
