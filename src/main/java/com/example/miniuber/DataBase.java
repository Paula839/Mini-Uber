package com.example.miniuber;

import java.sql.*;

public class Database {

    public static Statement statement;
    public static Connection connection;

    static {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/uber", "root", "???");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void store() throws SQLException {

    }
}
