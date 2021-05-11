package com.fehead.diseaseCare.socket;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TcpServerConfig {

    @Value("${tcp.port}")
    public Integer port;

    @Bean
    public CustomTcpServer customTcpServer(){
        CustomTcpServer customTcpServer = new CustomTcpServer(port);
        new Thread(new TcpConsumerThread(customTcpServer)).start();
        return customTcpServer;
    }

}

