package com.example.miniuber;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.stream.IntStream;

public class UIRequest extends Page implements Initializable, UIButtons, OnLogOut {
    Request request;
    static String  fromInput , toInput;
    static double costPremium = 0, costNormal = 0, costBus = 0, costMotorbike = 0;
    @FXML
    private ComboBox<String> fromCombo, toCombo;
    @FXML
    private Label premiumCostLabel, normalCostLabel, busCostLabel, motorCostLabel, wrongLabel, didNotChoose;
    @Override
    @FXML
    public void onGoBackClick(ActionEvent page) throws IOException {
        goTo(page, "Login");
    }

    @FXML
    public void onRequestClick(ActionEvent page) throws IOException {
        if(request.getFinalCost()==0) {
            didNotChoose.setText("Please choose location and car type first!");
        }

        else {
            didNotChoose.setText("");
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
            request.setFinalCost(costPremium);
            if(request.getFinalCost() == 0){
                didNotChoose.setText("Please choose a location first");
            }
        }
        else {
            premium.setOpacity(0);
            request.setFinalCost(0);
        }
    }
    @FXML
    public void onNormalClick()  {
        if(normal.getOpacity() == 0) {
            normal.setOpacity(1);
            premium.setOpacity(0);
            bus.setOpacity(0);
            motorbike.setOpacity(0);
            request.setFinalCost(costNormal);
            if(request.getFinalCost() == 0){
                didNotChoose.setText("Please choose a location first");
            }
        }

        else {
            normal.setOpacity(0);
            request.setFinalCost(0);
        }
    }
    @FXML
    public void onBusClick()  {
        if(bus.getOpacity() == 0) {
            bus.setOpacity(1);
            premium.setOpacity(0);
            normal.setOpacity(0);
            motorbike.setOpacity(0);
            request.setFinalCost(costBus);
            if(request.getFinalCost() == 0){
                didNotChoose.setText("Please choose a location first");
            }
        }

        else {
            bus.setOpacity(0);
            request.setFinalCost(0);
        }
    }
    @FXML
    public void onMotorClick()  {
        if(motorbike.getOpacity() == 0) {
            motorbike.setOpacity(1);
            premium.setOpacity(0);
            bus.setOpacity(0);
            normal.setOpacity(0);
            request.setFinalCost(costMotorbike);
            if(request.getFinalCost() == 0){
                didNotChoose.setText("Please choose a location first");
            }
        }

        else {
            motorbike.setOpacity(0);
            request.setFinalCost(0);
        }
    }
    @Override
    @FXML
    public void onSupportClick(ActionEvent page) throws IOException {
        savePage = "Request";
        goTo(page,"SupportTicket");
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

    public void OnConfirmLocClick() {
        fromInput = fromCombo.getValue();
        toInput = toCombo.getValue();
        Location location = new Location();
        int index1, index2;
        if(location.validate(fromInput, toInput)){
            index1 = location.getFromIndex(fromInput);
            index2 = location.getToIndex(toInput);
             request = new Request();
            costPremium = request.calculateCost(index1,index2,new Premium());
            costNormal = request.calculateCost(index1,index2,new Normal());
            costBus = request.calculateCost(index1,index2,new Bus());
            costMotorbike = request.calculateCost(index1,index2,new MotorBike());
            premiumCostLabel.setText(String.valueOf(costPremium));
            normalCostLabel.setText(String.valueOf(costNormal));
            busCostLabel.setText(String.valueOf(costBus));
            motorCostLabel.setText(String.valueOf(costMotorbike));
            wrongLabel.setText("");
            didNotChoose.setText("");
        }
        else{
            premiumCostLabel.setText("");
            normalCostLabel.setText("");
            busCostLabel.setText("");
            motorCostLabel.setText("");
            wrongLabel.setText("Please Choose the right Location!");
        }
    }
    @Override
    @FXML
    public void onAboutUsClick(ActionEvent page) throws IOException {
        savePage = "UIRequest";
        goTo(page, "AboutUs");
    }
    @Override
    @FXML
    public void onLogOutClick(ActionEvent page) throws IOException {
        goTo(page, "WelcomePage");
    }
}

class Request {
    private Calculator costCalculator;
    private double finalCost = 0;

    public Request() {
        costCalculator = new CostCalculator();
    }
    public double calculateCost(int fromIndex, int toIndex, Vehicle vehicle) {
        return costCalculator.calculate(fromIndex, toIndex)+ vehicle.cost;
    }

    public double getFinalCost() {
        return finalCost;
    }

    public void setFinalCost(double finalCost) {
        this.finalCost = finalCost;
    }
}
class Location {
    private static String[] locations; //vector better
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
    public boolean validate(String firstLocation, String secondLocation) {
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
abstract class Vehicle { //Factory
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

interface Calculator { // Strategy
    double calculate(int fromIndex, int toIndex);
}
class CostCalculator implements Calculator {
    private double costPerMile = 5;
    private double costPerMin = 0.3;
    private double bookingFee = 5;

    public double calculate(int fromIndex, int toIndex) {
        return (new DistanceCalculator().calculate(fromIndex, toIndex) * costPerMile)
                + (new TimeCalculator().calculate(fromIndex, toIndex) * costPerMin)
                + bookingFee;
    }
}

class DistanceCalculator implements Calculator {
    public double calculate(int fromIndex, int toIndex) {
        return Math.abs(fromIndex - toIndex);
    }
}


class TimeCalculator implements Calculator {
    public double calculate(int fromIndex, int toIndex) {
        return Math.abs(fromIndex - toIndex) * 10;
    }
}