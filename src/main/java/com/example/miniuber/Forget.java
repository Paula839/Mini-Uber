package com.example.miniuber;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.SQLException;

public class Forget extends DefaultSettings {
    public TextField emailText;
    public Label wrongInput;

    public Label username;
    public Label password;

    @FXML
    public void onGoBackClick(ActionEvent page) throws IOException {
        goTo(page, "Login");
    }
    @FXML
    public void validation() throws SQLException {
        Database.statement = Database.connection.createStatement();
        String command = "SELECT * FROM  user  WHERE email = " + '\"'
                + emailText.getText() + '\"';
        Database.statement = Database.connection.prepareStatement(command);
        Database.resultSet = Database.statement.executeQuery(command);
    }
    @FXML
    public void onFindClick() throws SQLException {
        validation();

        if (Database.resultSet.next()) {
           username.setText("Username: "+Database.resultSet.getString("username"));
           password.setText("Password: "+Database.resultSet.getString("password"));
            wrongInput.setText("");

        }

        else {
            wrongInput.setText("Invalid email!");
            username.setText("");
            password.setText("");
        }
    }

    @FXML
    public void onAboutUsClick(ActionEvent page) throws IOException {
        savePage = "Forget";
        goTo(page, "AboutUs");
    }




}
