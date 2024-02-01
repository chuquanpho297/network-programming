package com.networking.meetingclient.controller;

import com.networking.meetingclient.service.UserService;
import com.networking.meetingclient.util.Role;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

import static javafx.scene.input.MouseEvent.MOUSE_CLICKED;

public class RegisterController extends Controller implements Initializable {

    @FXML
    public Button registerButton;
    @FXML
    public ComboBox<String> role;
    @FXML
    public Label login;
    @FXML
    private TextField fullname;
    @FXML
    private PasswordField password;
    @FXML
    private TextField username;

    private UserService userService = UserService.getInstance();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        role.getItems().addAll(Role.STUDENT, Role.TEACHER);
        registerButton.addEventHandler(MOUSE_CLICKED, event -> {
            System.out.println("username: " + username.getText());
            System.out.println("password: " + password.getText());
            System.out.println("role: " + role.getValue());
            userService.register(username.getText(), password.getText(), fullname.getText(), role.getValue());

        });
        login.addEventHandler(MOUSE_CLICKED, event -> {
            try {
                switchToScreen((Stage) ((Node) event.getSource()).getScene().getWindow(), "login");
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }


}