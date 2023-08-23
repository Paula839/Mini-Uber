package com.example.miniuber;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public interface DefaultSettings {
    @FXML
    void onGoBackClick(ActionEvent event) throws IOException;
    @FXML
    void onSupportClick(ActionEvent event) throws IOException;
}
