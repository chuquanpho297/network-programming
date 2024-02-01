package com.networking.meetingclient.util;

import javafx.scene.control.Alert;

public class JavaFxUtil {
    public static void createAlert(String title, String headerText, String contentText) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);

        alert.showAndWait();
    }
}
