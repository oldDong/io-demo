package com.dongzj.aio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.util.concurrent.Future;

/**
 * User: dongzj
 * Mail: dongzj@shinemo.com
 * Date: 2019/1/25
 * Time: 10:55
 */
public class AIOClient {

    public static void main(String[] args) throws Exception {
        AsynchronousSocketChannel channel = AsynchronousSocketChannel.open();
        channel.connect(new InetSocketAddress("localhost", 8888)).get();
        ByteBuffer buffer = ByteBuffer.wrap("中文，你好".getBytes());
        Future<Integer> future = channel.write(buffer);

        future.get();
        System.out.println("send ok");
    }
}
