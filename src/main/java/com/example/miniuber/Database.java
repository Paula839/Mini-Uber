package com.example.miniuber;

import javafx.scene.Parent;

import java.sql.*;

public class Database {

    public static Statement statement;
    public static Connection connection;

     public static ResultSet resultSet;
    static {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/uber", "root", "12345678");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



}