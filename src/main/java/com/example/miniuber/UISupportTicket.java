package com.example.miniuber;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

import java.io.IOException;

public class UISupportTicket extends DefaultSettings {
    SupportTicketFactory supportTicketFactory;

    public TextField lostText,anotherProblemText;

    public ImageView lostImage, driverImage, paymentImage, anotherImage;
    @FXML
    private Label didNotChooseHelp, EmptyTextBox1, EmptyTextBox11;

    public void onLostClick() {
       if(lostImage.getOpacity() == 0) {
           lostText.setOpacity(1);
           lostImage.setOpacity(1);
           anotherImage.setOpacity(0);
           driverImage.setOpacity(0);
           paymentImage.setOpacity(0);
           anotherProblemText.setOpacity(0);
       }
       else{
           lostText.setOpacity(0);
           lostImage.setOpacity(0);
       }
    }

    public void onDriverClick() {

        if(driverImage.getOpacity() == 0) {
            lostText.setOpacity(0);
            lostImage.setOpacity(0);
            anotherImage.setOpacity(0);
            driverImage.setOpacity(1);
            paymentImage.setOpacity(0);
            anotherProblemText.setOpacity(0);
        }
        else{
            driverImage.setOpacity(0);

        }
    }

    public void onAnotherClick() {
        if(anotherImage.getOpacity() == 0) {
            anotherProblemText.setOpacity(1);
            lostText.setOpacity(0);
            lostImage.setOpacity(0);
            anotherImage.setOpacity(1);
            driverImage.setOpacity(0);
            paymentImage.setOpacity(0);
        }
        else{
            anotherImage.setOpacity(0);
            anotherProblemText.setOpacity(0);

        }
    }

    public void onPaymentClick() {
        if(paymentImage.getOpacity() == 0) {
            lostText.setOpacity(0);
            lostImage.setOpacity(0);
            anotherImage.setOpacity(0);
            driverImage.setOpacity(0);
            paymentImage.setOpacity(1);
            anotherProblemText.setOpacity(0);
        }
        else{
            paymentImage.setOpacity(0);

        }
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
        boolean isSubmitted = false;
      if(driverImage.getOpacity() == 1){
          supportTicketFactory = new DriverSupportTicketFactory();
          SupportTicket supportTicket = supportTicketFactory.createSupportTicket("About the driver ");

      }
      else if(anotherImage.getOpacity() == 1){
          supportTicketFactory = new AnotherSupportTicketFactory();
          if(anotherProblemText.getText() == null){
              EmptyTextBox1.setText("Please specify description first");
          }
          else {
              SupportTicket supportTicket = supportTicketFactory.createSupportTicket(anotherProblemText.getText());
          }
      }
      else  if(paymentImage.getOpacity() == 1){
          supportTicketFactory = new PaymentSupportTicketFactory();
          SupportTicket supportTicket = supportTicketFactory.createSupportTicket("About The Payment");
      }
      else if(lostImage.getOpacity() == 1){
          supportTicketFactory = new LostSupportTicketFactory();
          if(lostText.getText() == null){
              EmptyTextBox11.setText("Please specify description first");
          }
          else {
              SupportTicket supportTicket = supportTicketFactory.createSupportTicket(lostText.getText());
          }
      }
      else{
          didNotChooseHelp.setText("Please specify how can we support you");
      }
    }


    public void OnPaymentComplaintSubmitClick(ActionEvent actionEvent) {
    }

    public void OnSubmitDriverComplaintClick(ActionEvent actionEvent) {
    }
}

abstract class  SupportTicket implements Ticket {

    private int ticketId;
    private String Username;
    private String issueDescription;
    private boolean resolved;

    public SupportTicket( String issueDescription) {

        this.issueDescription = issueDescription;
        this.resolved = false;
    }


//    @Override
//    public int getTicketId() {
//        return ticketId;
//    }
//
//    @Override
//    public String getUsername() {
//        return Username;
//    }

    @Override
    public String getIssueDescription() {
        return issueDescription;
    }


    @Override
    public boolean isResolved() {
        return resolved;
    }

}