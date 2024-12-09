import java.sql.Connection;
import java.sql.DriverManager;

public class TestMySQLDriver {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/restaurant";
        String user = "root";
        String password = "1234";
        try {
            Class.forName("com.mysql.jdbc.Driver"); // 确保加载驱动
            Connection conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connection successful!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
