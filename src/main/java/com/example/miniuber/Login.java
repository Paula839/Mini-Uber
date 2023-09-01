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
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Login extends DefaultSettings {

    @FXML
    public  TextField username;
    public static TextField saveUsername;
    @FXML
    private TextField password;
    @FXML
    private Label wrongInput;

    @FXML
    public void onGoBackClick(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("WelcomePage.fxml"));
        Variables.stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Variables.scene = new Scene(root);
        Variables.stage.setScene(Variables.scene);
        Variables.stage.show();


    }

    @FXML
    public void validation() throws SQLException {
        Database.statement = Database.connection.createStatement();
        String command = "SELECT * FROM  user  WHERE username = " +  '\"'
                + username.getText() + '\"' + " AND password = " + '\"' + password.getText()  + '\"';
        Database.statement = Database.connection.prepareStatement(command);
        Database.resultSet = Database.statement.executeQuery(command);

    }

    @FXML

    public void onSigninClick(ActionEvent event) throws IOException, SQLException {

        validation();

        saveUsername=username;

        if (Database.resultSet.next()) {
            Parent root = FXMLLoader.load(getClass().getResource("Request.fxml"));
            Variables.stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            Variables.scene = new Scene(root);
            Variables.stage.setScene(Variables.scene);
            Variables.stage.show();

        } else

        wrongInput.setText("Invalid username or password!");




    }

    @FXML
    public void onSignupClick(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Register.fxml"));
        Variables.stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Variables.scene = new Scene(root);
        Variables.stage.setScene(Variables.scene);
        Variables.stage.show();
    }


    @FXML
    public void onSupportClick(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("SupportTicket.fxml"));
        Variables.stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Variables.scene = new Scene(root);
        Variables.stage.setScene(Variables.scene);
        Variables.stage.show();
    }

}
