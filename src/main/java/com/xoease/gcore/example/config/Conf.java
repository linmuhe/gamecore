package com.xoease.gcore.example.config;


import com.xoease.gcore.Ser;

import com.xoease.gcore.example.Server;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

 import java.net.InetSocketAddress;


@Configuration
public class Conf implements ApplicationContextAware {
    private ApplicationContext app;

    /**
     * 启动WWS
     */
   @Bean(initMethod = "start")
    public Ser start(SksConfig config) throws Exception {

       if(config.getHost().equals("")){
           return new Server(config.getPort(),app);
       }else{
           InetSocketAddress addr= new InetSocketAddress(config.getHost(),config.getPort());
           Server s = new Server(addr,app);
           return  s;
       }
    }


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.app= applicationContext;
    }
}
