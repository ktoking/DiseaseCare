package com.fehead.diseaseCare.socket;

import com.fehead.diseaseCare.service.IPatientHealthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

@Slf4j
public class TcpConsumerThread implements Runnable {
    //自定义Tcp服务端
    private CustomTcpServer customTcpServer;

    @Autowired
    private IPatientHealthService patientHealthService;

    public TcpConsumerThread(CustomTcpServer customTcpServer) {
        this.customTcpServer = customTcpServer;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Socket accept = customTcpServer.getServerSocket().accept();
                log.info("加入连接！！！");
                customTcpServer.getClientCount().getAndIncrement();
                log.info("当前连接数：" + customTcpServer.getClientCount().get());
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(accept.getInputStream()));
                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    log.info("收到消息：" + line);
                    Integer integer = patientHealthService.insertPatientHealth(line);
                    System.out.println(integer);
                }
                bufferedReader.close();
                accept.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
