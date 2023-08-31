package com.example.miniuber;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Login extends DefaultSettings {

    @FXML
    private TextField username;
    @FXML
    private TextField password;
    @FXML
    private Label wrongInput;

    @FXML
    public void onGoBackClick(ActionEvent page) throws IOException {
      goTo(page, "WelcomePage");
    }
    @FXML

    public void onSigninClick(ActionEvent page) throws IOException {
        if (username.getText().isEmpty() || username.getText().isEmpty())
            wrongInput.setText("Invalid username or password!");
        else {
            goTo(page, "Request");
        }
    }

    @FXML
    void onSignupClick(ActionEvent page) throws IOException {
        goTo(page, "Register");
    }


    @FXML
    public void onSupportClick(ActionEvent page) throws IOException {
        goTo(page, "SupportTicket");
    }





}
