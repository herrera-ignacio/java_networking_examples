import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws UnknownHostException {
        String hostname = "google.com";

        // getAllByName(hostname);
        // isReachable(hostname);
        isLoopbackAddress(hostname);
    }

    static void getAllByName(String hostname) throws UnknownHostException {
        InetAddress[] names = InetAddress.getAllByName(hostname);

        for (InetAddress name: names) {
            System.out.println(name);
        }
    }

    static void isReachable(String hostname) throws IOException {
        InetAddress address = InetAddress.getByName(hostname);

        if (address.isReachable(3))
            System.out.println("Reachable");
        else
            System.out.println("Not Reachable");
    }

    static void isLoopbackAddress(String hostname) {
        InetAddress address = InetAddress.getLoopbackAddress(); // localhost

        System.out.println("Address = " + Arrays.toString(address.getAddress()));

        if (address.isLoopbackAddress()) {
            System.out.println("Loopback Address");
        } else {
            System.out.println("Not Loopback Address");
        }
    }
}
