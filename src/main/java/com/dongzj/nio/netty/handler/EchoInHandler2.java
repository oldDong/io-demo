package com.dongzj.nio.netty.handler;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.Date;

/**
 * User: dongzj
 * Mail: dongzj@shinemo.com
 * Date: 2019/1/25
 * Time: 10:27
 */
public class EchoInHandler2 extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("EchoInHandler2 channelRead...");
        //Object msg 为Netty的一种缓存对象
        ByteBuf buffer = (ByteBuf) msg;
        byte[] req = new byte[buffer.readableBytes()];
        buffer.readBytes(req);
        String reqBody = new String(req,"UTF-8");
        System.out.println("获取到的客户端请求：" + reqBody);

        //往客户端写数据
        String date = new Date().toString();
        ByteBuf returnBuf = Unpooled.copiedBuffer(date.getBytes());
        ctx.write(returnBuf);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
