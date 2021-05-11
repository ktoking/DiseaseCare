package com.fehead.diseaseCare.nettySocket;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 自定义解码器
 */
@Slf4j
public class DecoderHandler extends ByteToMessageDecoder {

    private static Map<ChannelHandlerContext, String> msgBufMap = new ConcurrentHashMap<>();

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        byte[] data = new byte[in.readableBytes()];
        in.readBytes(data);
        String msg = new String(data, Charset.forName("utf-8"));
        out.add(msg);
//        // 处理粘包拆包问题
//        if (msg.startsWith("#")) {
//            if (msg.endsWith("#")) {
//                out.add(msg);
//            } else {
//                msgBufMap.put(ctx, msg);
//            }
//        } else if (msg.endsWith("#") && msgBufMap.containsKey(ctx)) {
//            msg = msgBufMap.get(ctx) + msg.split("#")[0];
//            out.add(msg);
//            msgBufMap.remove(ctx);
//        }
    }
}