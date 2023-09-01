package com.example.miniuber;


import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.io.IOException;



public class HelloController extends DefaultSettings{

    String sound = "D:\\CloneFinal\\Mini-Uber\\src\\main\\resources\\music\\eft7.mp3";
    Media media = new Media(new File(sound).toURI().toString());
    MediaPlayer mediaPlayer = new MediaPlayer(media);
    @FXML
    public void onGoButtonClick(ActionEvent page) throws IOException, InterruptedException {
//        mediaPlayer.play();
//        Thread.sleep(5000);
       goTo(page,"Login");
    }
    @FXML
    public void onAboutUsClick(ActionEvent page) throws IOException {
        goTo(page, "AboutUs");
    }

    @FXML
    public void onLogOutClick() throws IOException {
            Platform.exit();
    }

}