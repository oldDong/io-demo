package com.dongzj.io;

import java.io.*;
import java.net.Socket;

/**
 * 客户端Socket
 * <p>
 * User: dongzj
 * Mail: dongzj@shinemo.com
 * Date: 2019/1/24
 * Time: 19:38
 */
public class Client {

    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 10086);
            OutputStream outputStream = socket.getOutputStream();
            PrintWriter printWriter = new PrintWriter(outputStream);
            printWriter.write("天王盖地虎");
            printWriter.flush();
            socket.shutdownOutput();

            InputStream inputStream = socket.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String temp = null;
            String info = "";
            while ((temp = bufferedReader.readLine()) != null) {
                info += temp;
                System.out.println("answer: " + info);
            }

            bufferedReader.close();
            inputStreamReader.close();
            inputStream.close();
            printWriter.close();
            outputStream.close();
            socket.close();
        } catch (Exception e) {
            //TODO...
        }
    }
}
