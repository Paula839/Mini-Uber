package com.example.miniuber;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.SQLException;

public class UIForget extends Page implements UIButtons { //Factory
    public TextField emailText;
    public static String saveEmail;
    public Label wrongInput, username, password;
    @FXML
    public void onGoBackClick(ActionEvent page) throws IOException {
        goTo(page, "Login");
    }

    @FXML
    public void onAboutUsClick(ActionEvent page) throws IOException {
        savePage = "Forget";
        goTo(page, "AboutUs");
    }


    @Override
    public void onSupportClick(ActionEvent page) throws IOException {
        goTo(page, "SupportTicket");
    }



    @FXML
    public void onFindClick() throws SQLException {
        saveEmail = emailText.getText();
        ForgetValidation validation = new ForgetValidation();
        validation.validate();


        if (Database.resultSet.next()) {

            username.setText("Username: "+Database.resultSet.getString("username"));

            password.setText("Password: "+Database.resultSet.getString("password"));
            System.out.println(4);

            wrongInput.setText("");

        }

        else {
            wrongInput.setText("Invalid email!");
            username.setText("");
            password.setText("");
        }
    }
}

class ForgetValidation implements Validation { //Single Responsibility Principle,
    String email = UIForget.saveEmail;
    @Override
    @FXML
    public void validate() throws SQLException {
        Database.statement = Database.connection.createStatement();
        String command = "SELECT * FROM  user  WHERE email = " + '\"'
                + email + '\"';
        Database.statement = Database.connection.prepareStatement(command);
        Database.resultSet = Database.statement.executeQuery(command);
    }
}

