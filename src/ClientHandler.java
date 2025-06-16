import java.io.*;
import java.net.*;

public class ClientHandler implements Runnable {
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    private String username;

    public ClientHandler(Socket socket) {
        this.socket = socket;
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        try {
            out.println("Enter username:");
            username = in.readLine();
            out.println("Welcome, " + username);

            String msg;
            while ((msg = in.readLine()) != null) {
                System.out.println(username + ": " + msg);

                for (ClientHandler ch : ServerMain.clientHandlers) {
                    if (ch != this) {
                        ch.out.println(username + ": " + msg);
                    }
                }

                DBHelper.logMessage(username, "ALL", msg);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                ServerMain.clientHandlers.remove(this);
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
