import java.io.IOException;
import java.net.*;

/**
 * QOTD: Quote of the Day Protocol
 * https://www.gkbrk.com/wiki/qotd_protocol/
 */
public class Main {

    public static void main(String[] args) throws IOException {
        int port = 17; // QOTD uses 17 for UDP
        String hostname = "djxmmx.net";
        InetAddress address = InetAddress.getByName(hostname);

        DatagramSocket socket = new DatagramSocket();

        byte[] buffer = new byte[512];

        // Request
        DatagramPacket request = new DatagramPacket(buffer, buffer.length, address, port);
        socket.send(request);

        // Receive
        DatagramPacket response = new DatagramPacket(buffer, buffer.length);
        socket.receive(response);

        String quote = new String(buffer, 0, response.getLength());
        System.out.println(quote);
    }
}
