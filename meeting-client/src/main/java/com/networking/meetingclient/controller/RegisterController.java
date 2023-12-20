package com.networking.meetingclient.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

import static javafx.scene.input.MouseEvent.MOUSE_CLICKED;

public class RegisterController extends Controller implements Initializable {

    @FXML
    public PasswordField password;
    @FXML
    public TextField username;
    public Button registerButton;
    public ComboBox role;
    public Label login;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        role.getItems().addAll("Student", "Teacher");
        registerButton.addEventHandler(MOUSE_CLICKED, event -> {
            System.out.println("username: " + username.getText());
            System.out.println("password: " + password.getText());
            System.out.println("role: " + role.getValue());
        });
        login.addEventHandler(MOUSE_CLICKED, event -> {
            try {
                switchToScreen(event, "login");
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }


}