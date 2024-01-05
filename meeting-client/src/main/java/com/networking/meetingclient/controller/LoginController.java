package com.networking.meetingclient.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static javafx.scene.input.MouseEvent.MOUSE_CLICKED;

public class LoginController extends Controller implements Initializable {

    @FXML
    public PasswordField password;
    @FXML
    public TextField username;
    public Label register;

    @FXML
    private Button loginButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loginButton.addEventHandler(MOUSE_CLICKED, event -> {
            System.out.println("username: " + username.getText());
            System.out.println("password: " + password.getText());
            try {
                switchToScreen(event, "main");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        register.addEventHandler(MOUSE_CLICKED, event -> {
            try {
                switchToScreen(event, "register");
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }


}