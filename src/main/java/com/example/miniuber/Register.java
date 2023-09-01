package com.example.miniuber;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import java.io.IOException;
import java.sql.SQLException;
import java.util.regex.Pattern;

public class Register extends DefaultSettings implements Store{

    String SQL;
    String emailFormat ="^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
    Pattern pattern = Pattern.compile(emailFormat);
    @FXML
    private Label wrong;
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
       goTo(page, "Login");
    }
    @FXML
    public void onRegisterClick(ActionEvent page) throws IOException, SQLException {

        if (firstname.getText().isEmpty() || lastname.getText().isEmpty() ||
                username.getText().isEmpty() || password.getText().isEmpty() ||
                confirmPassword.getText().isEmpty() || email.getText().isEmpty()) {
            wrong.setText("Please Enter The TextField");

        }

        else if (!(password.getText().equals(confirmPassword.getText()))) {

            wrong.setText("Confirm password is not correct");
        }

        else if (!(pattern.matcher(email.getText()).matches())) {
            wrong.setText("Email Format is not correct");
        }

        else {
            store(SQL);
            goTo(page, "Login");
        }
    }

    @FXML
    public void onSupportClick(ActionEvent page) throws IOException {
        goTo(page, "SupportTicket");
    }

    @Override
    public void store(String SQL) throws SQLException {
        SQL = "INSERT INTO user VALUES( "+
                "\""+firstname.getText()+ "\" , "+
                "\""+lastname.getText()+ "\" , "+
                "\""+username.getText()+ "\" , "+
                "\""+password.getText()+ "\" , "+
                "\""+email.getText()+ "\"  "+
                ")";

        System.out.println(SQL);
        Database.statement = Database.connection.createStatement();

       Database.statement.executeUpdate(SQL);
    }
    @FXML
    public void onAboutUsClick(ActionEvent page) throws IOException {
        goTo(page, "AboutUs");
    }

}
