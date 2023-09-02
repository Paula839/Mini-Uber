package com.example.miniuber;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class UIDone extends DefaultSettings implements UIButtons, Initializable {
    @FXML
    Label paymentLabel;
    @FXML
  public void onShow() {
        if(UIPayment.card == 1) {
            CreditCardPaymentStrategy creditCard = new CreditCardPaymentStrategy(paymentLabel);
            creditCard.pay(UIPayment.cost);
        }

        else {
            CashPaymentStrategy cashPayment = new CashPaymentStrategy(paymentLabel);
            cashPayment.pay(UIPayment.cost);
        }
    }
    public void onLogOutClick(ActionEvent page) throws IOException {
        goTo(page,"WelcomePage");
    }
    public void onSupportClick(ActionEvent page) throws IOException {
        savePage = "Done";
        goTo(page,"SupportTicket");
    }

    public void onAboutUsClick(ActionEvent page) throws IOException {
        savePage = "Done";
        goTo(page,"AboutUs");
    }
    @Override
    public void onGoBackClick(ActionEvent page) throws IOException {
        savePage = "Done";
        goTo(page,"AboutUs");
    }


    @Override
    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {
        onShow();
    }
}



