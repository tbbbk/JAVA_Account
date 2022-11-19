package util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * tool class to connect Database
 */
public class DButil {
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static String DB_URL;
    static String USER;
    static String PASSWORD;
    static {
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public static Connection getConnection() throws IOException {
        String fileName = "db/data.txt";
        Stream<String> lines = Files.lines(Paths.get(fileName));
        List<String> originalData = lines.toList();
        DB_URL = originalData.get(0);
        USER = originalData.get(1);
        PASSWORD = originalData.get(2);
        Connection connection = null;
        try {
            System.out.println("Connecting database...");
            connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }

    public static void main(String[] args) throws IOException, SQLException {
        Connection connection = getConnection();
        Statement statement = null;
        statement = connection.createStatement();
        String sql = "SELECT ID, name from category";
        ResultSet result = statement.executeQuery(sql);
        while (result.next()) {
            int id = result.getInt("id");
            String name = result.getString("name");
            System.out.println("id:" + id + " name:" +name);
        }
        result.close();
        statement.close();
        connection.close();
    }
}
