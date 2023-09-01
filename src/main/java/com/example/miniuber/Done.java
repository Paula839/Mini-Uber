package com.example.miniuber;

import javafx.event.ActionEvent;

import java.io.IOException;

public class Done extends DefaultSettings{


    public void onSupportClick(ActionEvent page) throws IOException {
        savePage = "Done";
        goTo(page,"SupportTicket");
    }

    public void onLogOutClick(ActionEvent page) throws IOException {
        goTo(page,"WelcomePage");
    }

    public void onAboutUsClick(ActionEvent page) throws IOException {
        goTo(page,"AboutUs");
    }
}
