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

public class Register implements DefaultSettings {

    String EMAIL_REGEX = "^(.+)@(.+)$";
    @FXML
    private Label Wrong;
    @FXML
    private Label pass;
    @FXML
    private TextField Firstname;
    @FXML
    private TextField Lastname;
    @FXML
    private TextField Username;
    @FXML
    private TextField Password;
    @FXML
    private TextField Confirmpassword;
    @FXML
    private TextField Email;
    @Override
    @FXML
    public void onGoBackClick(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        Variables.stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Variables.scene = new Scene(root);
        Variables.stage.setScene(Variables.scene);
        Variables.stage.show();


    }

    @FXML
    public void onRegisterClick(ActionEvent event) throws IOException {

        if (Firstname.getText().isEmpty() || Lastname.getText().isEmpty() || Username.getText().isEmpty() || Password.getText().isEmpty() || Confirmpassword.getText().isEmpty() || Email.getText().isEmpty()) {
            Wrong.setText("Please Enter The TextField");


            ///if (!Objects.equals(Email,EMAIL_REGEX)){

            ///  Wrong.setText("Email format is not allowed");}
        }
        else if (!(Password.getText().equals(Confirmpassword.getText()))) {

            Wrong.setText("Confirm password is not correct");
        }
     ///   else if(!(Email.getText().contains("@"))) {
        else if(!(Email.getText().equals(EMAIL_REGEX))) {

            Wrong.setText("Email Format is not correct");

        }
        else{
            Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
            Variables.stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Variables.scene = new Scene(root);
            Variables.stage.setScene(Variables.scene);
            Variables.stage.show();
        }


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
