package com.example.miniuber;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Request {
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
        Parent root = FXMLLoader.load(getClass().getResource("Payment.fxml"));
        Variables.stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Variables.scene = new Scene(root);
        Variables.stage.setScene(Variables.scene);
        Variables.stage.show();
    }

    @FXML
    ImageView premium, normal, bus, motorbike;

    @FXML
    public void onPremiumClick()  {
        if(premium.getOpacity() == 0)
            premium.setOpacity(1);

        else
            premium.setOpacity(0);
    }
    @FXML
    public void onNormalClick()  {
        if(normal.getOpacity() == 0)
            normal.setOpacity(1);

        else
            normal.setOpacity(0);
    }
    @FXML
    public void onBusClick()  {
        if(bus.getOpacity() == 0)
            bus.setOpacity(1);

        else
            bus.setOpacity(0);
    }
    @FXML
    public void onMotorClick()  {
        if(motorbike.getOpacity() == 0)
            motorbike.setOpacity(1);

        else
            motorbike.setOpacity(0);
    }


}


