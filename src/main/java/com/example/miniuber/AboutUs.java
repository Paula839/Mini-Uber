package com.example.miniuber;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class AboutUs extends DefaultSettings{
    @FXML
    public void onGoBackClick(ActionEvent page) throws IOException {
        goTo(page, "WelcomePage");
    }
}
