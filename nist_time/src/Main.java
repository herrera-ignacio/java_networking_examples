import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class Main {

    public static void main(String[] args) {
        final String hostname = "time.nist.gov";
        int          port        = 13;

        try (Socket socket = new Socket(hostname, port)) {
            InputStream input = socket.getInputStream();
            InputStreamReader reader = new InputStreamReader(input);
            StringBuilder data = new StringBuilder();

            int character;

            while ((character = reader.read()) != -1) {
                data.append((char) character);
            }

            System.out.println(data);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
