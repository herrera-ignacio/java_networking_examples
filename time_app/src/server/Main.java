package server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Date;

public class Main {

    public static void main(String[] args) {
       log("Time server started");

       try {
           // create new socket
           ServerSocketChannel serverChannel = ServerSocketChannel.open();

           // bind socket to port 5000
           serverChannel.socket().bind(new InetSocketAddress(5000));

           while (true) {
               log("Waiting for Request...");

               SocketChannel socketChannel = serverChannel.accept();

               if (socketChannel != null) {
                   String dateTimeMessage = getDateTimeMessage();

                   ByteBuffer buffer = ByteBuffer.allocate(64);

                   buffer.put(dateTimeMessage.getBytes(StandardCharsets.UTF_8));

                   buffer.flip();

                   while (buffer.hasRemaining()) {
                       socketChannel.write(buffer);
                   }

                   log("Sent : " + dateTimeMessage);
               }
           }

       } catch (IOException e) {
           e.printStackTrace();
       }
    }

   static String getDateTimeMessage() {
        return "DATE : " + new Date(System.currentTimeMillis());
   }

    static void log(String data) {
        System.out.println("[SERVER] " + data);
    }
}
