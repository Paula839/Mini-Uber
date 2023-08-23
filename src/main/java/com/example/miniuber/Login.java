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

public class Login implements DefaultSettings {

    @FXML
    private TextField username;
    @FXML
    private TextField password;
    @FXML
    private Label wrongInput;
    @Override
    @FXML
    public void onGoBackClick(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("WelcomePage.fxml"));
        Variables.stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Variables.scene = new Scene(root);
        Variables.stage.setScene(Variables.scene);
        Variables.stage.show();


    }
    @FXML

    public void onSigninClick(ActionEvent event) throws IOException{
        if(username.getText().isEmpty() || username.getText().isEmpty())
                wrongInput.setText("Invalid username or password!");
        else {
            Parent root = FXMLLoader.load(getClass().getResource("Request.fxml"));
            Variables.stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            Variables.scene = new Scene(root);
            Variables.stage.setScene(Variables.scene);
            Variables.stage.show();
        }
    }

    @FXML
    public void onSignupClick(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Register.fxml"));
        Variables.stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Variables.scene = new Scene(root);
        Variables.stage.setScene(Variables.scene);
        Variables.stage.show();
    }

    @Override
    @FXML
    public void onSupportClick(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("SupportTicket.fxml"));
        Variables.stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Variables.scene = new Scene(root);
        Variables.stage.setScene(Variables.scene);
        Variables.stage.show();
    }


}
