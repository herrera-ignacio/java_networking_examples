import java.io.*;
import java.net.Socket;
import java.net.URL;

/**
 * HTTP Get Request/Response
 */
public class Main {
    public static void main(String[] args) throws IOException {

        // If no URL provided return
        if (args.length < 1) {
            System.out.println("No URL provided");
            return;
        };

        URL url = new URL(args[0]);
        String hostname = url.getHost();
        int port = 80;

        try (Socket socket = new Socket(hostname, port)) {
            // Request
            System.out.println("## REQUEST ##\n");

            OutputStream output = socket.getOutputStream();
            PrintWriter reqWriter = new PrintWriter(output, true);
            PrintWriter sysWriter = new PrintWriter(System.out, true);
            PrintWriter[] outs = {reqWriter, sysWriter};

            for (PrintWriter out: outs) {
                out.println("HOST: " + hostname);
                out.println("GET " + url.getPath() + " HTTP/1.1");
                out.println("USER AGENT: simple user agent");
                out.println("Accept: text/html");
                out.println("Connection: Close");
                out.println();
            }

            // Response
            System.out.println("## RESPONSE ##\n");

            InputStream input = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));

            System.out.println(reader.readLine());
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            reader.close();

            for (PrintWriter out: outs) {
                out.close();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
