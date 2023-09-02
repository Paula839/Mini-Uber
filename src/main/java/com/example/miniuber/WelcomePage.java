package com.example.miniuber;


import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.io.IOException;



public class WelcomePage extends DefaultSettings{


    @FXML
    public void onGoButtonClick(ActionEvent page) throws IOException, InterruptedException {
//        mediaPlayer.play();
//        Thread.sleep(5000);
       goTo(page,"Login");
    }
    @FXML
    public void onAboutUsClick(ActionEvent page) throws IOException {
        savePage = "WelcomePage";
        goTo(page, "AboutUs");
    }

    @FXML
    public void onLogOutClick() throws IOException {
            Platform.exit();
    }

}