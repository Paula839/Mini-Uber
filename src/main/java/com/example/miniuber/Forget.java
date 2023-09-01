package com.example.miniuber;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class Forget extends DefaultSettings {
    @FXML
    public void onGoBackClick(ActionEvent page) throws IOException {
        goTo(page, "Login");
    }

    @FXML
    public void onFindClick()  {

    }

    @FXML
    public void onAboutUsClick(ActionEvent page) throws IOException {
            goTo(page, "AboutUs");
    }




}
