package com.example.miniuber;

import javafx.event.ActionEvent;

import java.io.IOException;

public interface UIButtons extends OnAboutUs, OnSupport, OnGoBack {//Strategy, Command, Interface Segregation

}

interface OnAboutUs {
    void onAboutUsClick(ActionEvent page) throws IOException;
}

interface OnGoBack {
    void onGoBackClick(ActionEvent page) throws IOException;
}

interface OnSupport {
    void onSupportClick(ActionEvent page) throws IOException;
}

interface OnLogOut {
    void onLogOutClick(ActionEvent page) throws IOException;
}