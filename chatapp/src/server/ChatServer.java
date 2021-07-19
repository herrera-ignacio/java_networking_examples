package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

import static java.lang.System.out;

public class ChatServer {
    Vector<String> users = new Vector<String>();
    Vector<HandleClient> clients = new Vector<HandleClient>();

    public void process() throws Exception {
        ServerSocket server = new ServerSocket(9999, 10);
        out.println("Server started :");

        while (true) {
            Socket client = server.accept();
            HandleClient clientHandler = new HandleClient(client);
            clients.add(clientHandler);
        }
    }

    public static void main(String[] args) throws Exception {
        new ChatServer().process();
    }

    public void broadcast(String username, String msg) {
        for (HandleClient c : clients) {
            if (!c.getUsername().equals(username)) {
                c.sendMessage(username, msg);
            }
        }
    }

    class HandleClient extends Thread {
        String name = "";
        BufferedReader input;
        PrintWriter output;

        public HandleClient(Socket client) throws IOException {
            input = new BufferedReader(new InputStreamReader(client.getInputStream()));
            output = new PrintWriter(client.getOutputStream(), true);

            name = input.readLine();
            users.add(name);
            out.println(getUsername() + " -> joined\n");
            broadcast("Server", getUsername() + " joined!");
            start();
        }

        public void sendMessage(String username, String msg) {
            output.println(username + " : " + msg);
        }

        public String getUsername() {
            return name;
        }

        public void run() {
            String line;

            try {
                while (true) {
                    line = input.readLine();
                    out.println(getUsername() + " -> " + line + "\n");

                    if (line.equals("end")) {
                        clients.remove(this);
                        users.remove(name);
                        break;
                    }

                    broadcast(name, line);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

}
