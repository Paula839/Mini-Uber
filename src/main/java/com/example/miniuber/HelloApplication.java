package com.example.miniuber;

//import com.almasb.fxgl.net.Connection;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.sql.DriverManager;
import java.sql.ResultSet;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("WelcomePage.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Mini-Uber");
        stage.show();
    }

    public static void main(String[] args) throws SQLException {

    launch();
    }

}