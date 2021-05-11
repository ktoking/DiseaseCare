package com.fehead.diseaseCare.nettySocket;

import com.fehead.diseaseCare.service.IPatientHealthService;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;

/**
 * 自定义服务端处理器
 */
@Component
@Slf4j
@ChannelHandler.Sharable
public class ServerHandler extends ChannelInboundHandlerAdapter {

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Autowired
    private IPatientHealthService patientHealthService;

    /**
     * 在与客户端的连接已经建立之后将被调用
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.info("netty客户端与服务端连接开始...");
    }

    /**
     * 当从客户端接收到一个消息时被调用
     * msg 就是硬件传送过来的数据信息
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        this.getGisLocation(msg.toString());   //这是下面自己写的业务逻辑处理的方法
    }



    /**
     * 客户端与服务端断开连接时调用
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        log.info("netty客户端与服务端连接关闭...");
    }

    /**
     * 服务端接收客户端发送过来的数据结束之后调用
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
        log.info("信息接收完毕...");
    }

    /**
     * 在处理过程中引发异常时被调用
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
        System.out.println("异常信息：rn " + cause.getMessage());
    }



    /**
     * 获取定位数据逻辑
     *
     * @param msg
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
     void getGisLocation(String msg) {
        log.info("获取数据: " + msg);
        Integer insert = patientHealthService.insertPatientHealth(msg);
        log.info("插入数据:"+insert);
    }
}