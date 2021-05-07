package com.fehead.diseaseCare.socket;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.atomic.AtomicInteger;

@Data
@Slf4j
public class CustomTcpServer {
    //记录Tcp连接数
    private volatile AtomicInteger clientCount = new AtomicInteger(0);
    //ServerSocket服务端对象
    private volatile ServerSocket serverSocket;

    /**
     * 双重锁初始化自定义Tcp服务端
     *
     * @param port
     */
    public CustomTcpServer(Integer port) {
        if (serverSocket == null) {
            synchronized (CustomTcpServer.class) {
                if (serverSocket == null) {
                    try {
                        serverSocket = new ServerSocket(port,3);
                    } catch (IOException e) {
                        e.printStackTrace();
                        log.info("......初始化Tcp服务端失败，请检查端口是否占用");
                    }
                    log.info("......初始化Tcp服务端成功");
                }
            }
        }
    }
}
