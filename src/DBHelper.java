import java.sql.*;

public class DBHelper {
    private static final String URL = "jdbc:postgresql://localhost:5432/chatapp";
    private static final String USER = "postgres";
    private static final String PASS = "Aman@9163";

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static boolean validateUser(String username, String password) {
        try (Connection con = DriverManager.getConnection(URL, USER, PASS)) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM users WHERE username=? AND password=?");
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean registerUser(String username, String password) {
        try (Connection con = DriverManager.getConnection(URL, USER, PASS)) {
            PreparedStatement ps = con.prepareStatement("INSERT INTO users(username, password) VALUES(?, ?)");
            ps.setString(1, username);
            ps.setString(2, password);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public static void logMessage(String sender, String receiver, String message) {
        try (Connection con = DriverManager.getConnection(URL, USER, PASS)) {
            PreparedStatement ps = con.prepareStatement("INSERT INTO messages(sender, receiver, message) VALUES(?, ?, ?)");
            ps.setString(1, sender);
            ps.setString(2, receiver);
            ps.setString(3, message);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
