package com.example.miniuber;

<<<<<<< HEAD
import javafx.scene.Parent;

=======
>>>>>>> origin/master
import java.sql.*;

public class Database {

    public static Statement statement;
    public static Connection connection;

<<<<<<< HEAD
     public static ResultSet resultSet;
    static {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/uber", "root", "12345678");
=======
    static {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/uber", "root", "???");
>>>>>>> origin/master
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

<<<<<<< HEAD


}
=======
    public static void store() throws SQLException {

    }
}
>>>>>>> origin/master
