package com.dongzj.io;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Socket服务端
 *
 * User: dongzj
 * Mail: dongzj@shinemo.com
 * Date: 2019/1/24
 * Time: 19:31
 */
public class Server {

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(10086);
            System.out.println("服务端已启动，等待客户端连接..");
            Socket socket = serverSocket.accept();

            InputStream inputStream = socket.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String temp = null;
            String info = "";
            while ((temp = bufferedReader.readLine()) != null) {
                info += temp;
                System.out.println("已接收到客户端连接");
                System.out.println("服务端接收到客户端信息：" + info +"，当前客户端ip: " + socket.getInetAddress().getHostAddress());

                OutputStream outputStream = socket.getOutputStream();
                PrintWriter printWriter = new PrintWriter(outputStream);
                printWriter.write("宝塔镇河妖");
                printWriter.flush();
                socket.shutdownOutput();

                printWriter.close();
                outputStream.close();
                bufferedReader.close();
                inputStream.close();
                socket.close();
            }
        } catch(Exception e) {
            //TODO...
        }
    }
}
