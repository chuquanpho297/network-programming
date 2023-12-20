package com.networking.meetingclient;

import com.networking.meetingclient.config.FxmlConfig;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class HelloApplication extends Application {
    public static void main(String[] args) {
//        TCPClient client = new TCPClient(9000, "localhost");
        try {
            launch();
        } finally {
//            client.closeSocketConnection();
        }
    }

    @Override
    public void start(Stage stage) throws Exception {
        // Load the FXML file for the login screen
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource(FxmlConfig.MAIN.getFxmlPath()));
        // Create the scene with the root node loaded from the FXML file
        Scene scene = new Scene(loader.load());
        String css = this.getClass().getResource(FxmlConfig.MAIN.getCssPath()).toExternalForm();
        scene.getStylesheets().add(css);
        stage.setScene(scene);

        // Set the title for the stage
        stage.setTitle("Appointment management system");

        // Show the stage
        stage.show();
    }
}