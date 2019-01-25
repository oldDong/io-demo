package com.dongzj.nio.netty.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;

/**
 * User: dongzj
 * Mail: dongzj@shinemo.com
 * Date: 2019/1/25
 * Time: 10:32
 */
public class EchoOutHandler2 extends ChannelOutboundHandlerAdapter {

    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        System.out.println("EchoOutHandler2 write...");
        ctx.write(msg);
    }
}
