package com.example.miniuber;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import java.io.IOException;
import java.sql.SQLException;

public class Login extends DefaultSettings {


    @FXML
    public TextField username;
    public static TextField saveUsername;
    @FXML
    private TextField password;

    @FXML
    private Label wrongInput;
    @FXML
    public void onGoBackClick(ActionEvent page) throws IOException {
        goTo(page, "WelcomePage");
    }
    @FXML
    public void validation() throws SQLException {
        Database.statement = Database.connection.createStatement();
        String command = "SELECT * FROM  user  WHERE username = " + '\"'
                + username.getText() + '\"' + " AND password = " + '\"' + password.getText() + '\"';
        Database.statement = Database.connection.prepareStatement(command);
        Database.resultSet = Database.statement.executeQuery(command);
    }

    @FXML
    public void onSignInClick(ActionEvent page) throws IOException, SQLException {
        validation();
        saveUsername = username;
        if (Database.resultSet.next()) {
            goTo(page, "Request");
        }

        else {
            wrongInput.setText("Invalid username or password!");
        }
    }
    @FXML
    public void onSignUpClick(ActionEvent page) throws IOException {
        goTo(page, "Register");
    }
    @FXML
    public void onForgetClick(ActionEvent page) throws IOException {
        goTo(page, "Forget");
    }
    @FXML
    public void onAboutUsClick(ActionEvent page) throws IOException {
        savePage = "Login";
        goTo(page, "AboutUs");
    }
}
