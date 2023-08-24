package com.example.miniuber;

import java.sql.*;

public class DataBase  {

    public static Connection connection;
    public static Statement statement;
    public static ResultSet resultSet;

    public static void store()

    {
        try {
             connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/uber", "root", "12345678");
             statement = connection.createStatement();
             resultSet = statement.executeQuery("select * from supporttickets");

            while (resultSet.next()) {
                System.out.println(resultSet.getString("message"));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
