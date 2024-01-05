package com.networking.meetingclient.controller;

import com.networking.meetingclient.HelloApplication;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Function;

public class MainController extends Controller implements Initializable {
    @FXML
    private VBox body;

    @FXML
    private Label fullname;

    @FXML
    private Label homelabel;

    @FXML
    private Label meetinglabel;

    @FXML
    private Label profilelabel;

    @FXML
    private Label smeetinglabel;

    @FXML
    private ProgressIndicator progressIndicator;

    @FXML
    private Button logoutBtn;

    private void setMainBody(String fxmlPath, Controller controller) {
        try {
            body.getChildren().clear();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(HelloApplication.class.getResource(fxmlPath));
            loader.setController(controller);
            Node node = loader.load();
            body.getChildren().add(node);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.setMainBody("main/time_slot.fxml", new TimeSlotController(progressIndicator));


        setClickable(smeetinglabel, event -> {
            this.setMainBody("main/s_meeting.fxml", new SchedulingMeetingController(progressIndicator));
            return null;
        });

        setClickable(meetinglabel, event -> {
            this.setMainBody("main/time_slot.fxml", new TimeSlotController(progressIndicator));
            return null;
        });

        setClickable(profilelabel, event -> {
//            this.setMainBody("main/profile.fxml");
            return null;
        });

        setClickable(logoutBtn, event -> {
            System.out.println("logoutBtn has been clicked");
            try {
                switchToScreen(event, "login");
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        });

    }

    private void setClickable(Node node, Function<Event, Void> func) {
        node.setOnMouseClicked(func::apply);
    }


}
