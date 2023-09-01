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
import java.sql.SQLException;
import java.util.regex.Pattern;

public class Register extends DefaultSettings implements Store{

    String sql;
    String regexPattern2 ="^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
    Pattern pattern= Pattern.compile(regexPattern2);
    @FXML
    private Label Wrong;
    @FXML
    private Label pass;
    @FXML
    private TextField Firstname;
    @FXML
    private TextField Lastname;
    @FXML
    public  static TextField username;
    @FXML
    public  static TextField Password;
    @FXML
    private TextField Confirmpassword;
    @FXML
    public  static TextField Email;

    @FXML
    public void onGoBackClick(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        Variables.stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Variables.scene = new Scene(root);
        Variables.stage.setScene(Variables.scene);
        Variables.stage.show();


    }

    @FXML
    public void onRegisterClick(ActionEvent event) throws IOException, SQLException {

        if (Firstname.getText().isEmpty() || Lastname.getText().isEmpty() || username.getText().isEmpty() || Password.getText().isEmpty() || Confirmpassword.getText().isEmpty() || Email.getText().isEmpty()) {
            Wrong.setText("Please Enter The TextField");

        }
        else if (!(Password.getText().equals(Confirmpassword.getText()))) {

            Wrong.setText("Confirm password is not correct");
        }

     else if (!(pattern.matcher(Email.getText()).matches())){

            Wrong.setText("Email Format is not correct");

        }
        else{
            store(sql);
            Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
            Variables.stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Variables.scene = new Scene(root);
            Variables.stage.setScene(Variables.scene);
            Variables.stage.show();

        }


    }


    @FXML
    public void onSupportClick(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("SupportTicket.fxml"));
        Variables.stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Variables.scene = new Scene(root);
        Variables.stage.setScene(Variables.scene);
        Variables.stage.show();

    }

    @Override
    public void store(String sql) throws SQLException {
        sql = "INSERT INTO user VALUES( "+
                "\""+Firstname.getText()+ "\" , "+
                "\""+Lastname.getText()+ "\" , "+
                "\""+username.getText()+ "\" , "+
                "\""+Password.getText()+ "\" , "+
                "\""+Email.getText()+ "\"  "+
                ")";

        System.out.println(sql);

        Database.statement = Database.connection.createStatement();

       Database.statement.executeUpdate(sql);

    }

}
