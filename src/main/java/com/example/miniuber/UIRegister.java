package com.example.miniuber;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import java.io.IOException;
import java.sql.SQLException;
import java.util.regex.Pattern;

public class UIRegister extends DefaultSettings implements UIButtons {

    public static String sql;
    String emailFormat ="^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
    Pattern pattern = Pattern.compile(emailFormat);
    @FXML
    private Label wrong;
    @FXML
    private TextField firstname, lastname, username, password, confirmPassword, email;
    @Override
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
            ReadAccount readAccount = new ReadAccount(
                    firstname.getText(), lastname.getText(), username.getText(), password.getText(),
                    email.getText()
                    );

            readAccount.read();
            Database.store(sql);
            goTo(page, "Login");
        }
    }
    @Override
    @FXML
    public void onSupportClick(ActionEvent page) throws IOException {
        goTo(page, "SupportTicket");
    }

    @Override
    @FXML
    public void onAboutUsClick(ActionEvent page) throws IOException {
        savePage = "Register";
        goTo(page, "AboutUs");
    }
}

class ReadAccount implements Read {
    String firstname,  lastname,  username,  password, email;
    ReadAccount(String firstname, String lastname, String username, String password, String email) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.password = password;
        this.email = email;
    }
    @Override
    public void read() {
        UIRegister.sql = "INSERT INTO user VALUES( "+
                "\""+firstname+ "\" , "+
                "\""+lastname+ "\" , "+
                "\""+username+ "\" , "+
                "\""+password+ "\" , "+
                "\""+email+ "\"  "+
                ")";
    }
}
