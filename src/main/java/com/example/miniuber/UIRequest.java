package com.example.miniuber;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.List;
import static java.lang.Math.abs;
import java.util.stream.IntStream;

public class UIRequest implements Initializable{
    double finalCost = 0;
    String FromLocInput , ToLocInput;
    double CostPermuim=0, CostNormal=0, CostBus=0, CostMotorbike=0;
    @FXML
    private ComboBox<String> FromLoc_Combo,ToLoc_combo;
    @FXML
    private Label PermuimCostLabel,NormalCostLabel,BusCostLabel,MotorCostLabel,wrongLocLabel,DidNotChose;
    @FXML
    public void onGoBackClick(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        Variables.stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Variables.scene = new Scene(root);
        Variables.stage.setScene(Variables.scene);
        Variables.stage.show();

    }
    @FXML
    public void onRequestClick(ActionEvent event) throws IOException {
        if(finalCost==0){
            DidNotChose.setText("Please choose location and car type first");
        }
        else {
            DidNotChose.setText("");
            Parent root = FXMLLoader.load(getClass().getResource("Payment.fxml"));
            Variables.stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Variables.scene = new Scene(root);
            Variables.stage.setScene(Variables.scene);
            Variables.stage.show();
        }
    }

    @FXML
    ImageView premium, normal, bus, motorbike;

    @FXML
    public void onPremiumClick()  {
        if(premium.getOpacity() == 0) {
            premium.setOpacity(1);
            normal.setOpacity(0);
            bus.setOpacity(0);
            motorbike.setOpacity(0);
            finalCost = CostPermuim;
            if(finalCost == 0){
                DidNotChose.setText("Please choose a location first");
            }
        }
        else {
            premium.setOpacity(0);
            finalCost =0;
        }
    }
    @FXML
    public void onNormalClick()  {
        if(normal.getOpacity() == 0) {
            normal.setOpacity(1);
            premium.setOpacity(0);
            bus.setOpacity(0);
            motorbike.setOpacity(0);
            finalCost = CostNormal;
            if(finalCost == 0){
                DidNotChose.setText("Please choose a location first");
            }
        }
        else {
            normal.setOpacity(0);
            finalCost = 0;
        }
    }
    @FXML
    public void onBusClick()  {
        if(bus.getOpacity() == 0) {
            bus.setOpacity(1);
            premium.setOpacity(0);
            normal.setOpacity(0);
            motorbike.setOpacity(0);
            finalCost = CostBus;
            if(finalCost == 0){
                DidNotChose.setText("Please choose a location first");
            }
        }
        else {
            bus.setOpacity(0);
            finalCost=0;
        }
    }
    @FXML
    public void onMotorClick()  {
        if(motorbike.getOpacity() == 0) {
            motorbike.setOpacity(1);
            premium.setOpacity(0);
            bus.setOpacity(0);
            bus.setOpacity(0);
            finalCost = CostMotorbike;
            if(finalCost == 0){
                DidNotChose.setText("Please choose a location first");
            }
        }
        else {
            motorbike.setOpacity(0);
            finalCost=0;
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        FromLoc_Combo.setItems(FXCollections.observableArrayList("Elkurba","Roxy","Heliopolis","Safeer","Sheraton","Abbasia","Ramses","Ghamra","Tahrir","5th Settlement","Zamalek","Makram Abeid","Haram","Agouza","Abour"));
        ToLoc_combo.setItems(FXCollections.observableArrayList("Elkurba","Roxy","Heliopolis","Safeer","Sheraton","Abbasia","Ramses","Ghamra","Tahrir","5th Settlement","Zamalek","Makram Abeid","Haram","Agouza","Abour"));
    }

    public void OnConfirmLocClick(ActionEvent actionEvent) {

        FromLocInput = FromLoc_Combo.getValue();
        ToLocInput = ToLoc_combo.getValue();
        loaction l = new loaction();
        int index1, index2;
        if(l.validateLoc(FromLocInput,ToLocInput)){
            index1 = l.getFromindex(FromLocInput);
            index2 = l.getToindex(ToLocInput);
            Request r = new Request();
            CostPermuim = r.calculateCost(index1,index2,new Premium());
            CostNormal = r.calculateCost(index1,index2,new Normal());
            CostBus = r.calculateCost(index1,index2,new Bus());
            CostMotorbike = r.calculateCost(index1,index2,new MotorBike());
           PermuimCostLabel.setText(String.valueOf(CostPermuim));
           NormalCostLabel.setText(String.valueOf(CostNormal));
            BusCostLabel.setText(String.valueOf(CostBus));
            MotorCostLabel.setText(String.valueOf(CostMotorbike));
            wrongLocLabel.setText("");
            DidNotChose.setText("");
        }
        else{
            PermuimCostLabel.setText("");
            NormalCostLabel.setText("");
            BusCostLabel.setText("");
            MotorCostLabel.setText("");
            wrongLocLabel.setText("Please Choose the right Location");
        }

    }
}

class Request {

    private Calculator costCalculator;

    public Request() {

        costCalculator = new CostCalculator();
    }


    public double calculateCost(int fromIndex, int toIndex, vehicle v) {
        return costCalculator.calculate(fromIndex, toIndex)+ v.cost;
    }
}
class loaction{
    private static String[] locations;
    public  loaction(){
        locations = new String[15];
        locations[0] ="Elkurba";
        locations[1] ="Roxy";
        locations[2] ="Heliopolis";
        locations[3] ="Safeer";
        locations[4] ="Sheraton";
        locations[5] ="Makram Abeid";
        locations[6] ="Abbasia";
        locations[7] ="Ramses";
        locations[8] ="Ghamra";
        locations[9] ="Agouza";
        locations[10] ="Zamalek";
        locations[11] ="Tahrir";
        locations[12] ="Haram";
        locations[13] ="5th Settlement";
        locations[14] ="Abour";
    }
    public boolean validateLoc(String firstLoc, String SecondLoc){
        return (firstLoc != SecondLoc);
    }
    public int getFromindex(String firstLoc){
         int len = locations.length;
        return IntStream.range(0, len)
                .filter(i -> firstLoc == locations[i])
                .findFirst() // first occurrence
                .orElse(-1);

    }
    public int getToindex(String SecondLoc){
        int len = locations.length;
        return IntStream.range(0, len)
                .filter(i -> SecondLoc == locations[i])
                .findFirst() // first occurrence
                .orElse(-1);

    }


}
abstract class vehicle{
    float cost;
    public vehicle(float cost){
        this.cost = cost;
    }
}
class Premium extends vehicle{
    public Premium(){
        super(20);
    }
}
class Normal extends vehicle{
    public Normal(){
        super(10);
    }
}
class Bus extends vehicle{
    public Bus(){
        super(5);
    }
}class MotorBike extends vehicle{
    public MotorBike(){
        super(7);
    }
}


