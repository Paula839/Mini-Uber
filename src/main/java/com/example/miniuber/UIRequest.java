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
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.List;
import static java.lang.Math.abs;
import java.util.stream.IntStream;
import com.example.miniuber.Payment;

public class UIRequest extends DefaultSettings implements Initializable {
    double finalCost = 0;
   static String  fromInput , toInput;
    static double costPremium = 0, costNormal = 0, costBus = 0, costMotorbike = 0;
    @FXML
    private ComboBox<String> fromCombo,toCombo;
    @FXML
    private Label premuimCostLabel, normalCostLabel, busCostLabel, motorCostLabel, wrongLabel, didNotChose;
    @FXML
    public void onGoBackClick(ActionEvent page) throws IOException {
        goTo(page, "Login");
    }



    @FXML
    public void onRequestClick(ActionEvent page) throws IOException {
        if(finalCost==0){
            didNotChose.setText("Please choose location and car type first!");
        }
        else {
            didNotChose.setText("");
            savePremium=premium;
            saveNormal=normal;
            saveBus=bus;
            saveMotorbike=motorbike;
           goTo(page, "Payment");
        }
    }

    @FXML
   public  ImageView premium, normal, bus, motorbike;
   public  static ImageView savePremium, saveNormal, saveBus, saveMotorbike;

    @FXML
    public void onPremiumClick()  {
        if(premium.getOpacity() == 0) {
            premium.setOpacity(1);
            normal.setOpacity(0);
            bus.setOpacity(0);
            motorbike.setOpacity(0);
            finalCost = costPremium;
            if(finalCost == 0){
                didNotChose.setText("Please choose a location first");
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
            finalCost = costNormal;
            if(finalCost == 0){
                didNotChose.setText("Please choose a location first");
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
            finalCost = costBus;
            if(finalCost == 0){
                didNotChose.setText("Please choose a location first");
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
            finalCost = costMotorbike;
            if(finalCost == 0){
                didNotChose.setText("Please choose a location first");
            }
        }
        else {
            motorbike.setOpacity(0);
            finalCost=0;
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        fromCombo.setItems(FXCollections.observableArrayList("Elkurba", "Roxy", "Heliopolis",
                "Safeer", "Sheraton", "Abbasia", "Ramses", "Ghamra", "Tahrir", "5th Settlement",
                "Zamalek", "Makram Abeid", "Haram", "Agouza", "Abour"));
        toCombo.setItems(FXCollections.observableArrayList("Elkurba", "Roxy", "Heliopolis", "Safeer",
                "Sheraton", "Abbasia", "Ramses", "Ghamra", "Tahrir", "5th Settlement", "Zamalek",
                "Makram Abeid", "Haram", "Agouza", "Abour"));
    }

    public void OnConfirmLocClick(ActionEvent actionEvent) {
        fromInput = fromCombo.getValue();
        toInput = toCombo.getValue();
        Location location = new Location();
        int index1, index2;
        if(location.validate(fromInput, toInput)){
            index1 = location.getFromIndex(fromInput);
            index2 = location.getToIndex(toInput);
            Request request = new Request();
            costPremium = request.calculateCost(index1,index2,new Premium());
            costNormal = request.calculateCost(index1,index2,new Normal());
            costBus = request.calculateCost(index1,index2,new Bus());
            costMotorbike = request.calculateCost(index1,index2,new MotorBike());
            premuimCostLabel.setText(String.valueOf(costPremium));
            normalCostLabel.setText(String.valueOf(costNormal));
            busCostLabel.setText(String.valueOf(costBus));
            motorCostLabel.setText(String.valueOf(costMotorbike));
            wrongLabel.setText("");
            didNotChose.setText("");
        }
        else{
            premuimCostLabel.setText("");
            normalCostLabel.setText("");
            busCostLabel.setText("");
            motorCostLabel.setText("");
            wrongLabel.setText("Please Choose the right Location!");
        }

    }
}

class Request {

    private Calculator costCalculator;

    public Request() {

        costCalculator = new CostCalculator();
    }


    public double calculateCost(int fromIndex, int toIndex, Vehicle vehicle) {
        return costCalculator.calculate(fromIndex, toIndex)+ vehicle.cost;
    }
}
class Location {
    private static String[] locations;
    public  Location() {
        locations = new String[15];
        locations[0] = "Elkurba";
        locations[1] = "Roxy";
        locations[2] = "Heliopolis";
        locations[3] = "Safeer";
        locations[4] = "Sheraton";
        locations[5] = "Makram Abeid";
        locations[6] = "Abbasia";
        locations[7] = "Ramses";
        locations[8] = "Ghamra";
        locations[9] = "Agouza";
        locations[10] = "Zamalek";
        locations[11] = "Tahrir";
        locations[12] = "Haram";
        locations[13] = "5th Settlement";
        locations[14] = "Abour";
    }
    public boolean validate(String firstLocation, String secondLocation){
        return (firstLocation != secondLocation);
    }
    public int getFromIndex(String firstLocation){
        int length = locations.length;
        return IntStream.range(0, length)
                .filter(i -> firstLocation == locations[i])
                .findFirst() // first occurrence
                .orElse(-1);

    }
    public int getToIndex(String secondLocation){
        int length = locations.length;
        return IntStream.range(0, length)
                .filter(i -> secondLocation == locations[i])
                .findFirst() // first occurrence
                .orElse(-1);

    }


}
abstract class Vehicle {
    float cost;
    public Vehicle(float cost) {
        this.cost = cost;
    }
}
class Premium extends Vehicle {
    public Premium(){
        super(20);
    }
}
class Normal extends Vehicle {
    public Normal(){
        super(10);
    }
}
class Bus extends Vehicle {
    public Bus(){
        super(5);
    }
}class MotorBike extends Vehicle {
    public MotorBike(){
        super(7);
    }
}