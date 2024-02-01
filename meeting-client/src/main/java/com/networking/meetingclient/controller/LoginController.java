package com.networking.meetingclient.controller;

import com.networking.meetingclient.StateManager;
import com.networking.meetingclient.service.UserService;
import com.networking.meetingclient.util.Role;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static javafx.scene.input.MouseEvent.MOUSE_CLICKED;

public class LoginController extends Controller implements Initializable {

    private final UserService userService = UserService.getInstance();
    @FXML
    public PasswordField password;
    @FXML
    public TextField username;
    public Label register;
    @FXML
    private Button loginButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (StateManager.getInstance().getUser() != null) {
            try {
                if (StateManager.getInstance().getUser().getRole().equals(Role.STUDENT))
                    switchToScreen((Stage) loginButton.getScene().getWindow(), "student_main");
                else if (StateManager.getInstance().getUser().getRole().equals(Role.TEACHER))
                    switchToScreen((Stage) loginButton.getScene().getWindow(), "teacher_main");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        loginButton.addEventHandler(MOUSE_CLICKED, event -> {
            System.out.println("username: " + username.getText());
            System.out.println("password: " + password.getText());

            userService.login(username.getText(), password.getText());

            try {
                if (username.getText().equals("1"))
                    switchToScreen((Stage) ((Node) event.getSource()).getScene().getWindow(), "student_main");
                else if (username.getText().equals("2"))
                    switchToScreen((Stage) ((Node) event.getSource()).getScene().getWindow(), "teacher_main");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        register.addEventHandler(MOUSE_CLICKED, event -> {
            try {
                switchToScreen((Stage) ((Node) event.getSource()).getScene().getWindow(), "register");
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }


}