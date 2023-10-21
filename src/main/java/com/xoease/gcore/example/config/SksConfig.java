package com.xoease.gcore.example.config;

 import org.springframework.beans.factory.annotation.Value;
 import org.springframework.context.annotation.Configuration;
 import org.springframework.stereotype.Component;
@Component
@Configuration

public class SksConfig {

    @Value("${sks.port}")
    private  int port ;
    @Value("${sks.host}")
    private   String host ;

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }
}
