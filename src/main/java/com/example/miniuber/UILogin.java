package com.example.miniuber;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

public class UILogin extends DefaultSettings implements OnGoBack, OnAboutUs {
    String sound = "D:\\CloneFinal2\\Mini-Uber\\src\\main\\resources\\music\\aboMohamed.mp3";
    Media media = new Media(new File(sound).toURI().toString());
    MediaPlayer mediaPlayer = new MediaPlayer(media);
    String sound2 = "D:\\CloneFinal2\\Mini-Uber\\src\\main\\resources\\music\\mbshofsh.mp3";
    Media media2 = new Media(new File(sound2).toURI().toString());
    MediaPlayer mediaPlayer2 = new MediaPlayer(media2);
    @FXML
    public TextField username, password;
    public static String saveUsername, savePassword;
    @FXML
    private Label wrongInput;
    @FXML
    public void onGoBackClick(ActionEvent page) throws IOException {
        goTo(page, "WelcomePage");
    }

    @FXML
    public void onSignInClick(ActionEvent page) throws IOException, SQLException, InterruptedException {
        saveUsername = username.getText();
        savePassword = password.getText();
        LoginValidation validation = new LoginValidation();
        validation.validate();
        if (Database.resultSet.next()) {
            if (Database.resultSet.getString("first_name").equals("Mohamed")) {
                mediaPlayer.play();
            }
            else {
                mediaPlayer2.play();
            }

            Thread.sleep(5000);
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

class LoginValidation implements Validation {

    @Override
    public void validate() throws SQLException {
        String username = UILogin.saveUsername, password = UILogin.savePassword;
        Database.statement = Database.connection.createStatement();
        String command = "SELECT * FROM  user  WHERE username = " + '\"'
                + username + '\"' + " AND password = " + '\"' + password + '\"';
        Database.statement = Database.connection.prepareStatement(command);
        Database.resultSet = Database.statement.executeQuery(command);
    }
}

