import java.io.*;
import java.net.*;
import java.util.*;

public class ServerMain {
    public static Set<ClientHandler> clientHandlers = Collections.synchronizedSet(new HashSet<>());

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(5000);
        System.out.println("Server started on port 5000");

        while (true) {
            Socket socket = serverSocket.accept();
            ClientHandler clientHandler = new ClientHandler(socket);
            clientHandlers.add(clientHandler);
            new Thread(clientHandler).start();
        }
    }
}
