package com.networking.meetingclient.controller.student;

import com.networking.meetingclient.HelloApplication;
import com.networking.meetingclient.controller.Controller;
import com.networking.meetingclient.service.UserService;
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

public class StudentMainController extends Controller implements Initializable {
    @FXML
    public Label title;
    @FXML
    public Label meetinglabel;

    @FXML
    private VBox body;

    @FXML
    private Label smeetinglabel;

    @FXML
    private ProgressIndicator progressIndicator;

    @FXML
    private Button logoutBtn;

    private UserService userService = UserService.getInstance();

    private void setMainBody(String fxmlPath, Controller controller) {
        progressIndicator.setVisible(false);
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
        this.setMainBody("student_main/time_slot.fxml", new TimeSlotController(progressIndicator));
        setTitle("Time Slots", title);

        setClickable(smeetinglabel, event -> {
            this.setMainBody("student_main/s_meeting.fxml", new StudentSchedulingMeetingController(progressIndicator));
            setTitle("Scheduled Meetings", title);
            return null;
        });

        setClickable(meetinglabel, event -> {
            this.setMainBody("student_main/time_slot.fxml", new TimeSlotController(progressIndicator));
            setTitle("Time Slots", title);
            return null;
        });

        setClickable(logoutBtn, event -> {
            System.out.println("logoutBtn has been clicked");
            try {
                userService.logout();
                switchToScreen(event, "login");
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        });

    }
}
