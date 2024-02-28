package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataSource {
    private static Connection cnx;
    private static String url = "jdbc:mysql://localhost:3306/campigoo";
    private static String login = "root";
    private static String pwd = "";
    private static DataSource instance;

    // Private constructor to prevent direct instantiation
    private DataSource() {
        try {
            // Load the JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Initialize the connection
            cnx = DriverManager.getConnection(url, login, pwd);
            System.out.println("Connection successful!");
        } catch (ClassNotFoundException | SQLException e) {
            // Properly handle exceptions (log, display, etc.)
            e.printStackTrace();
            throw new RuntimeException("Failed to initialize the database connection.", e);
        }
    }

    // Method to get the unique instance of DataSource
    public static synchronized DataSource getInstance() {
        if (instance == null) {
            instance = new DataSource();
        }
        return instance;
    }

    // Method to get the database connection
    public Connection getCnx() {
        return cnx;
    }

    // Add a method to close the connection if needed
    public void closeConnection() {
        try {
            if (cnx != null && !cnx.isClosed()) {
                cnx.close();
                System.out.println("Connection closed.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
