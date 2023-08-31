package com.example.miniuber;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;

public class Payment {
    @FXML
    ImageView credit, cash;

    @FXML
    public void onCreditClick()  {
        if(credit.getOpacity() == 0) {
            credit.setOpacity(1);
            cash.setOpacity(0);
        }
        else
            credit.setOpacity(0);
    }   @FXML
    public void onCashClick()  {
        if(cash.getOpacity() == 0) {
            cash.setOpacity(1);
            credit.setOpacity(0);
        }
        else
            cash.setOpacity(0);
    }
}
