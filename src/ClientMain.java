import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;

public class ClientMain {
    private static BufferedReader in;
    private static PrintWriter out;

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 5000);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);

        JFrame frame = new JFrame("Login");
        JTextField userField = new JTextField(15);
        JPasswordField passField = new JPasswordField(15);
        JButton loginBtn = new JButton("Login");
        JButton regBtn = new JButton("Register");

        JPanel panel = new JPanel();
        panel.add(new JLabel("Username:")); panel.add(userField);
        panel.add(new JLabel("Password:")); panel.add(passField);
        panel.add(loginBtn); panel.add(regBtn);

        frame.add(panel);
        frame.setSize(300, 150);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        loginBtn.addActionListener(e -> {
            String user = userField.getText();
            String pass = new String(passField.getPassword());
            if (DBHelper.validateUser(user, pass)) {
                frame.dispose();
                launchChat(socket, user);
            } else {
                JOptionPane.showMessageDialog(frame, "Invalid login");
            }
        });

        regBtn.addActionListener(e -> {
            String user = userField.getText();
            String pass = new String(passField.getPassword());
            if (DBHelper.registerUser(user, pass)) {
                JOptionPane.showMessageDialog(frame, "Registration successful!");
            } else {
                JOptionPane.showMessageDialog(frame, "User already exists.");
            }
        });
    }

    public static void launchChat(Socket socket, String username) {
        JFrame chatFrame = new JFrame("Chat - " + username);
        JTextArea chatArea = new JTextArea(20, 40);
        JTextField inputField = new JTextField(30);
        JButton sendBtn = new JButton("Send");

        chatArea.setEditable(false);
        chatFrame.setLayout(new BorderLayout());
        chatFrame.add(new JScrollPane(chatArea), BorderLayout.CENTER);

        JPanel inputPanel = new JPanel();
        inputPanel.add(inputField);
        inputPanel.add(sendBtn);
        chatFrame.add(inputPanel, BorderLayout.SOUTH);

        chatFrame.pack();
        chatFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        chatFrame.setVisible(true);

        sendBtn.addActionListener(e -> {
            String msg = inputField.getText();
            out.println(msg);
            chatArea.append("Me: " + msg + "\n");
            inputField.setText("");
        });

        new Thread(() -> {
            String msg;
            try {
                while ((msg = in.readLine()) != null) {
                    chatArea.append(msg + "\n");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
