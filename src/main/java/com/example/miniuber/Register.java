package com.example.miniuber;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;
import java.util.regex.Pattern;

public class Register extends DefaultSettings {


    String emailPattern ="^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
    Pattern pattern= Pattern.compile(emailPattern);
    @FXML
    private Label wrongLabel;
    @FXML
    private Label passwordLabel;
    @FXML
    private TextField firstname;
    @FXML
    private TextField lastname;
    @FXML
    private TextField username;
    @FXML
    private TextField password;
    @FXML
    private TextField confirmPassword;
    @FXML
    private TextField email;

    @FXML
    public void onGoBackClick(ActionEvent page) throws IOException {
        goTo(page ,"Login");
    }

    @FXML
    public void onRegisterClick(ActionEvent page) throws IOException {

        if (firstname.getText().isEmpty() || lastname.getText().isEmpty() || username.getText().isEmpty()
                || password.getText().isEmpty() || confirmPassword.getText().isEmpty()
                || email.getText().isEmpty()) {
            wrongLabel.setText("Please Enter The TextField");
        } else if (!(password.getText().equals(confirmPassword.getText()))) {
            wrongLabel.setText("Confirm password is not correct");
        } else if (!(pattern.matcher(email.getText()).matches())) {
            wrongLabel.setText("Email Format is not correct");
        } else {
            goTo(page, "Login");
        }
    }
    @FXML
    public void onSupportClick(ActionEvent page) throws IOException {
        goTo(page, "SupportTicket");
    }

}
