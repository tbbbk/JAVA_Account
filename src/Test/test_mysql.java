package Test;

import java.sql.*;

public class test_mysql {
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://1.15.92.33:3306/hutubill";
    static final String USER = "root";
    static final String PASSWORD = "Kuizaidsg23333.";
    public static void main(String[] args) throws SQLException {
        Connection connection = null;
        Statement statement = null;
        try {
            Class.forName(JDBC_DRIVER);
            System.out.println("Connecting database...");
            connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            System.out.println("Instantiating a Statement object...");
            statement = connection.createStatement();
            String sql = "SELECT ID, name from category";
            ResultSet result = statement.executeQuery(sql);
            while (result.next()) {
                int id = result.getInt("id");
                String name = result.getString("name");
                System.out.println("id:" + id + " n ame:" +name);
            }
            result.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            System.out.println("Goodbye!");
        }
    }
}
