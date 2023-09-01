package com.example.miniuber;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import java.io.IOException;
import java.sql.SQLException;

public class Payment extends DefaultSettings  implements Store {

   public ImageView credit;
   public  ImageView cash;

   public TextField creditNumbers;

    private String SQL;
    public Label wrong;

    @FXML
    public void onGoBackClick(ActionEvent page) throws IOException {
        goTo(page,"Request");

    }

    @FXML
    public void onCardClick(){
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
    public void onCashClick(){
        if(cash.getOpacity()==0) {
            cash.setOpacity(1);
            credit.setOpacity(0);
            creditNumbers.setOpacity(0);
        }
        else
            cash.setOpacity(0);
    }

    public void onSubmitClick() throws SQLException {

        if(cash.getOpacity()==1){
            //store username,from,to,transport,price,payment method
            store(SQL);

        }
        else if(credit.getOpacity()==1){

            if(creditNumbers.getLength()<16||creditNumbers.getLength()>19){
                   wrong.setText("Please enter a valid Credit card!");
            }
            else{
                store(SQL);
                //store creditNumbers
            }
        }
        else{
            wrong.setText("Please select a payment method!");
        }
    }
    public void onSupportClick(ActionEvent page) throws IOException {
        goTo(page,"SupportTicket");
    }

    @Override
    public void store(String sql) throws SQLException {

        String transportation;

        if( UIRequest.savePremium.getOpacity() == 1){
            transportation="Premium";
        }
        else if(UIRequest.saveNormal.getOpacity() == 1){
            transportation="Normal";
        }
        else if(UIRequest.saveBus.getOpacity() == 1){
            transportation="Bus";
        }
        else {
            transportation="Motorbike";
        }

        double cost;
        if(UIRequest.costPremium>0){
            cost=UIRequest.costPremium;
        }
        else if(UIRequest.costNormal>0){
            cost=UIRequest.costNormal;
        }
        else if(UIRequest.costBus>0){
            cost=UIRequest.costBus;
        }
        else {
            cost=UIRequest.costMotorbike;
        }

        String paymentMethod;

        if(credit.getOpacity()==1) {
            paymentMethod="Credit";
        }
        else {
            paymentMethod="Cash";

        }

        String creditNumber="";
        if(credit.getOpacity()==1)
            creditNumber=": "+creditNumbers.getText();

        sql = "INSERT INTO request VALUES( "+
                "\""+Login.saveUsername.getText()+ "\" , "+
                "\""+UIRequest.fromInput+ "\" , "+
                "\""+UIRequest.toInput+ "\" , "+
                "\""+transportation+ "\" , "+
                "\""+cost+ "\"  ,"+
                "\""+paymentMethod+creditNumber+ "\"  "+
                ")";



        System.out.println(sql);

        Database.statement = Database.connection.createStatement();

        Database.statement.executeUpdate(sql);
    }

    @FXML
    public void onAboutUsClick(ActionEvent page) throws IOException {
        goTo(page, "AboutUs");
    }

    @FXML
    public void onLogOutClick(ActionEvent page) throws IOException {
        goTo(page, "WelcomePage");
    }
}
