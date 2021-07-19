package client;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

public class Main {

    public static void main(String[] args) {
        SocketAddress socketAddress = new InetSocketAddress("127.0.0.1", 5000);

        try (SocketChannel socketChannel = SocketChannel.open(socketAddress)) {
            ByteBuffer buffer = ByteBuffer.allocate(64);

            int bytesRead = socketChannel.read(buffer);

            StringBuilder data = new StringBuilder();

            while (bytesRead > 0) {
                buffer.flip();

                data.append(StandardCharsets.UTF_8.decode(buffer).toString());

                bytesRead = socketChannel.read(buffer);
            }

            System.out.println(data.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
