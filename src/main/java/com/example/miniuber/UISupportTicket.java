package com.example.miniuber;

import javafx.event.ActionEvent;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

import java.io.IOException;

public class UISupportTicket extends DefaultSettings {

     public TextField lostText;

     public ImageView lostImage, driverImage, paymentImage, anotherImage;


    public void onLostClick() {
        lostText.setOpacity(1);
        lostImage.setOpacity(1);
        anotherImage.setOpacity(0);
        driverImage.setOpacity(0);
        paymentImage.setOpacity(0);
    }

    public void onDriverClick() {
        lostText.setOpacity(0);
        lostImage.setOpacity(0);
        anotherImage.setOpacity(0);
        driverImage.setOpacity(1);
        paymentImage.setOpacity(0);
    }

    public void onAnotherClick() {
        lostText.setOpacity(0);
        lostImage.setOpacity(0);
        anotherImage.setOpacity(1);
        driverImage.setOpacity(0);
        paymentImage.setOpacity(0);
    }

    public void onPaymentClick() {
        lostText.setOpacity(0);
        lostImage.setOpacity(0);
        anotherImage.setOpacity(0);
        driverImage.setOpacity(0);
        paymentImage.setOpacity(1);
    }

    public void onGoBackClick(ActionEvent page) throws IOException {
        goTo(page, savePage);
    }

    public void onAboutUsClick(ActionEvent page) throws IOException {
        goTo(page, "AboutUs");
    }

    public void onLogOutClick(ActionEvent page) throws IOException {
        goTo(page, "WelcomePage");
    }

    public void onButtonClick(ActionEvent page) throws IOException {

    }


}

abstract class  SupportTicket implements Ticket {

    private int ticketId;
    private String Username;
    private String issueDescription;
    private boolean resolved;

    public SupportTicket(int ticketId, String Username, String issueDescription) {
        this.ticketId = ticketId;
        this.Username = Username;
        this.issueDescription = issueDescription;
        this.resolved = false;
    }


    @Override
    public int getTicketId() {
        return ticketId;
    }

    @Override
    public String getUsername() {
        return Username;
    }

    @Override
    public String getIssueDescription() {
        return issueDescription;
    }


    @Override
    public boolean isResolved() {
        return resolved;
    }

}