package com.dongzj.nio.netty;

import com.dongzj.nio.netty.handler.EchoInHandler1;
import com.dongzj.nio.netty.handler.EchoInHandler2;
import com.dongzj.nio.netty.handler.EchoOutHandler1;
import com.dongzj.nio.netty.handler.EchoOutHandler2;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * Netty服务端实现
 *
 * User: dongzj
 * Mail: dongzj@shinemo.com
 * Date: 2019/1/25
 * Time: 09:53
 */
public class EchoServer {

    private final int port;

    public EchoServer(int port) {
        this.port = port;
    }

    public void start() throws InterruptedException {
        //创建线程组
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            //创建服务端启动对象 装配线程组、交互通道、服务器端口、网络请求处理器链
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(group)
                    .channel(NioServerSocketChannel.class)
                    .localAddress("localhost", port)
                    .childHandler(new ChannelInitializer<Channel>() {
                        @Override
                        protected void initChannel(Channel ch) throws Exception {
                            ch.pipeline().addLast(new EchoOutHandler1());
                            ch.pipeline().addLast(new EchoOutHandler2());
                            ch.pipeline().addLast(new EchoInHandler1());
                            ch.pipeline().addLast(new EchoInHandler2());
                        }
                    });

            //开始监听客户端请求
            ChannelFuture channelFuture = serverBootstrap.bind().sync();
            System.out.println("开始监听，端口号为：" + channelFuture.channel());

            //等待所有请求执行完毕后，关闭通道；如请求还没执行完，这里为阻塞状态
            channelFuture.channel().closeFuture().sync();
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            //停止所有线程组内部代码的执行
            group.shutdownGracefully().sync();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        EchoServer server = new EchoServer(8080);
        server.start();
    }
}
