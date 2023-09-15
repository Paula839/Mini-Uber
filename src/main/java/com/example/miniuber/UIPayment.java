
package com.example.miniuber;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import java.io.IOException;
import java.sql.SQLException;

public class UIPayment extends Page implements UIButtons {
    public static double cost;
    public ImageView credit, cash;
    public static int card = 0; //0 for null ,1 for credit, 2 for cash
    public  TextField creditNumbers;
    public static String saveCreditNumbers;
    public static String sql;
    public Label wrong;

    @FXML
    public void onGoBackClick(ActionEvent page) throws IOException {
        goTo(page,"Request");
    }
    @FXML
    public void onCardClick() {
        if(credit.getOpacity()==0) {
            credit.setOpacity(1);
            creditNumbers.setOpacity(1);
            cash.setOpacity(0);
        }
        else {
            credit.setOpacity(0);
            creditNumbers.setOpacity(0);
        }
    }
    @FXML
    public void onCashClick() {
        if (cash.getOpacity() == 0) {
            cash.setOpacity(1);
            credit.setOpacity(0);
            creditNumbers.setOpacity(0);
        }
        else {
            cash.setOpacity(0);
        }
    }

    @FXML
    public void onSubmitClick(ActionEvent page) throws SQLException, IOException {

        ReadPayment readPayment = new ReadPayment();
        if(cash.getOpacity()==1) {
            card = 2;
            System.out.println(sql);
            readPayment.read();
            Database.store(sql);
            goTo(page,"Done");
        }

        else if(credit.getOpacity()==1) {

            if(creditNumbers.getLength()<16||creditNumbers.getLength()>19) {
                wrong.setText("Please enter a valid Credit card!");
            }
            else {
                card = 1;
                System.out.println(sql);
                readPayment.read();
                Database.store(sql);
                goTo(page,"Done");
            }
        }

        else {
            wrong.setText("Please select a payment method!");
        }
    }
    @FXML
    public void onSupportClick(ActionEvent page) throws IOException {
        savePage = "Payment";
        goTo(page,"SupportTicket");
    }
    @FXML
    public void onAboutUsClick(ActionEvent page) throws IOException {
        goTo(page, "AboutUs");
    }

    @FXML
    public void onLogOutClick(ActionEvent page) throws IOException {
        savePage = "Payment";
        goTo(page, "WelcomePage");
    }
}

class ReadPayment extends UIPayment implements Read {


    @Override
    public void read()  {
        String transportation;
        if(UIRequest.savePremium.getOpacity() == 1) {
            transportation="Premium";
        }

        else if(UIRequest.saveNormal.getOpacity() == 1) {
            transportation="Normal";
        }

        else if(UIRequest.saveBus.getOpacity() == 1) {
            transportation="Bus";
        }

        else {
            transportation="Motorbike";
        }


        if(UIRequest.costPremium>0) {
            cost=UIRequest.costPremium;
        }

        else if(UIRequest.costNormal>0) {
            cost=UIRequest.costNormal;
        }

        else if(UIRequest.costBus>0) {
            cost=UIRequest.costBus;
        }

        else {
            cost=UIRequest.costMotorbike;
        }

        String paymentMethod;
        System.out.println("cardaya "+UIPayment.card);
        if(card == 2) {
            paymentMethod="Cash";
        }
        else {
            paymentMethod="Credit";
        }





        sql = "INSERT INTO request VALUES( "+
                "\""+UILogin.saveUsername+ "\" , "+
                "\""+UIRequest.fromInput+ "\" , "+
                "\""+UIRequest.toInput+ "\" , "+
                "\""+transportation+ "\" , "+
                "\""+cost+ "\"  ,"+
                "\""+paymentMethod+ "\"  "+
                ")";
    }
}

interface PaymentStrategy {
    void pay(double cost);
}

class CashPaymentStrategy implements PaymentStrategy {
    Label paymentLabel;
    CashPaymentStrategy(Label paymentLabel) {
        this.paymentLabel = paymentLabel;
    }
    public void pay(double cost) {
        paymentLabel.setText("You have paid cash " + cost + " $ successfully.");
    }
}

class CreditCardPaymentStrategy implements PaymentStrategy {
    Label paymentLabel;
    CreditCardPaymentStrategy(Label paymentLabel) {
        this.paymentLabel = paymentLabel;
    }
    public void pay(double cost) {
        paymentLabel.setText("You have paid credit " + cost + " $ successfully.");
    }
}


