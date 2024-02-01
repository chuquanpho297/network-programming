package com.networking.meetingclient.controller.teacher;

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
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class TeacherMainController extends Controller implements Initializable {
    @FXML
    public Label title;
    @FXML
    public Label dmeetinglabel;
    @FXML
    public Label historylabel;
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
        this.setMainBody("teacher_main/s_meeting.fxml", new TeacherSchedulingMeetingController(progressIndicator));
        setTitle("Meetings", title);

        setClickable(smeetinglabel, event -> {
            this.setMainBody("teacher_main/s_meeting.fxml", new TeacherSchedulingMeetingController(progressIndicator));
            setTitle("Meetings", title);
            return null;
        });

        setClickable(dmeetinglabel, event -> {
            this.setMainBody("teacher_main/declare_time_slot.fxml", new DeclareTimeSlotController(progressIndicator));
            setTitle("Declare Time Slots", title);
            return null;
        });

        setClickable(historylabel, event -> {
            this.setMainBody("teacher_main/past_meeting.fxml", new TeacherPastMeetingController(progressIndicator));
            setTitle("History", title);
            return null;
        });


        setClickable(logoutBtn, event -> {
            System.out.println("logoutBtn has been clicked");
            try {
                userService.logout();
                switchToScreen((Stage) ((Node) event.getSource()).getScene().getWindow(), "login");
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        });

    }
}
