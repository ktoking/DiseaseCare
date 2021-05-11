//package com.fehead.diseaseCare.socket;
//
//import com.fehead.diseaseCare.socket.CustomTcpServer;
//import com.fehead.diseaseCare.socket.TcpConsumerThread;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class TcpServerConfig {
//
//    @Value("${tcp.port:17782}")
//    public Integer port;
//
//    @Bean
//    public CustomTcpServer customTcpServer(){
//        CustomTcpServer customTcpServer = new CustomTcpServer(port);
//        new Thread(new TcpConsumerThread(customTcpServer)).start();
//        return customTcpServer;
//    }
//
//}
//
