import java.io.*;
import java.net.Socket;

public class Main {

    // whois.internic.net:43
    public static void main(String[] args) throws IOException {
        if (args.length < 1) {
            System.out.println("No arg provided");
            return;
        }

        String domainName = args[0];
        String hostname = "whois.internic.net";
        int port = 43;

        try (Socket socket = new Socket(hostname, port)) {
            OutputStream output = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(output, true);
            writer.println(domainName);

            InputStream input = socket.getInputStream();

            BufferedReader reader = new BufferedReader(new InputStreamReader(input));

            String line;

            while((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException ioEx) {
            ioEx.printStackTrace();
        }
    }
}
