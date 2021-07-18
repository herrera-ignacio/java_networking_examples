import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Collections;
import java.util.Enumeration;

public class Main {

    public static void main(String[] args) {
        try {
            Enumeration<NetworkInterface> networkIfs = NetworkInterface.getNetworkInterfaces();
            System.out.println("Network Display: \n");
            for(NetworkInterface element : Collections.list(networkIfs)) {
                System.out.printf("%-8s %-32s \n", element.getName(), element.getDisplayName());
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }
}
