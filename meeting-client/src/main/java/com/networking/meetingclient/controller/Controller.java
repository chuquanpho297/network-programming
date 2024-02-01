package com.networking.meetingclient.controller;

import com.networking.meetingclient.HelloApplication;
import com.networking.meetingclient.models.Meeting;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;
import java.util.function.Function;

public class Controller {

    protected ProgressIndicator progressIndicator;

    public void switchToScreen(Stage stage, String fxmlDir) throws IOException {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(HelloApplication.class.getResource(fxmlDir + "/index.fxml")));
            root.getStylesheets().add(HelloApplication.class.getResource(fxmlDir + "/styles.css").toExternalForm());
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void setTitle(String title, Label titleLabel) {
        titleLabel.setText(title);
    }

    public void setClickable(Node node, Function<Event, Void> func) {
        node.setOnMouseClicked(func::apply);
    }

    public void showMeetingDetail(Meeting meeting) {
        try {
            FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("meeting_detail.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Meeting Detail");
            loader.setController(new MeetingDetailController(meeting));
            // Set the scene and show the stage
            Scene scene = new Scene(loader.load()); // Set appropriate size
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}