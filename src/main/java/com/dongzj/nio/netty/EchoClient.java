package com.dongzj.nio.netty;

import com.dongzj.nio.netty.handler.EchoClientHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * Netty 客户端实现
 *
 * User: dongzj
 * Mail: dongzj@shinemo.com
 * Date: 2019/1/25
 * Time: 10:17
 */
public class EchoClient {

    private final String host;
    private final int port;

    public EchoClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    private void start() throws InterruptedException {
        //创建线程组
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            //创建客户端启动对象，同样需要装配线程组，通道，绑定远程地址，请求处理器链
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group)
                    .channel(NioSocketChannel.class)
                    .remoteAddress(host, port)
                    .handler(new ChannelInitializer<Channel>() {
                        @Override
                        protected void initChannel(Channel ch) throws Exception {
                            ch.pipeline().addLast(new EchoClientHandler());
                        }
                    });

            //开始请求连接
            ChannelFuture future = bootstrap.connect().sync();

            //当请求操作结束后，关闭通道
            future.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (group != null) {
                group.shutdownGracefully().sync();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        EchoClient client = new EchoClient("localhost", 8080);
        client.start();
    }
}
