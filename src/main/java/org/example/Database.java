package org.example;

import java.sql.*;

public class Database {
    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/casino";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "123123";

    public static void init() throws SQLException, ClassNotFoundException {
        Connection connection = Database.getConnection();
        Class.forName(JDBC_DRIVER);
        Statement statement = connection.createStatement();

        statement.executeUpdate("CREATE TABLE IF NOT EXISTS PLAYERS (" + // create a PLAYERS table
                "ID INT PRIMARY KEY AUTO_INCREMENT," +
                "NAME VARCHAR(32) NOT NULL," +
                "MONEY INT NOT NULL" +
                ");");

        statement.executeUpdate("CREATE TABLE IF NOT EXISTS CROUPIER (" +
                "TOTAL_MONEY INT NOT NULL" +
                ");");

        statement.close();
        connection.close();
    }

    public static Connection getConnection() throws SQLException {
        Connection connection;
        connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
        connection.setAutoCommit(true);

        return connection;
    }

}