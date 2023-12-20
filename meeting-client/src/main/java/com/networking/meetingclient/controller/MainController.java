package com.networking.meetingclient.controller;

import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.util.Callback;

import java.net.URL;
import java.time.LocalTime;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    public ListView<TimeSlot> timeSlotList;

    public void initialize() {

    }

    public void addExampleTimeSlot() {
        // Create an example time slot
        TimeSlot exampleTimeSlot = new TimeSlot("Monday", LocalTime.of(9, 0), LocalTime.of(10, 0), "Teacher Name");
        TimeSlot exampleTimeSlot2 = new TimeSlot("Monday", LocalTime.of(9, 0), LocalTime.of(10, 0), "Teacher Name");
        TimeSlot exampleTimeSlot3 = new TimeSlot("Monday", LocalTime.of(9, 0), LocalTime.of(10, 0), "Teacher Name");

        // Get the current items in the ListView
        ObservableList<TimeSlot> items = this.timeSlotList.getItems();

        // Add the example time slot to the items
        items.add(exampleTimeSlot);
        items.add(exampleTimeSlot2);
        items.add(exampleTimeSlot3);

        // Update the items in the ListView
        timeSlotList.setItems(items);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        timeSlotList.setCellFactory(new TimeSlotCellFactory());
        addExampleTimeSlot();
    }
}

class TimeSlotCellFactory implements Callback<ListView<TimeSlot>, ListCell<TimeSlot>> {
    @Override
    public ListCell<TimeSlot> call(ListView<TimeSlot> param) {
        return new ListCell<>() {
            @Override
            protected void updateItem(TimeSlot item, boolean empty) {
                super.updateItem(item, empty);
                if (item != null) {
                    HBox hbox = new HBox(10); // Add spacing between components
                    hbox.getStyleClass().add("time-slot"); // Add CSS class
                    hbox.setStyle("-fx-border-color: black; -fx-border-width: 1;"); // Add border
                    hbox.setAlignment(Pos.CENTER); // Center the components
                    hbox.setStyle("-fx-background-color: white; -fx-padding: 10;"); // Add background color and padding

                    Label dayLabel = new Label(item.day());
                    dayLabel.setStyle("-fx-font-weight: bold;"); // Make the day label bold

                    Label startTimeLabel = new Label(item.startTime().toString());
                    Label endTimeLabel = new Label(item.endTime().toString());
                    Label teacherNameLabel = new Label(item.teacherName());

                    hbox.getChildren().addAll(dayLabel, startTimeLabel, endTimeLabel, teacherNameLabel);
                    setGraphic(hbox);
                } else {
                    setGraphic(null);
                }
            }
        };
    }


}


record TimeSlot(String day, LocalTime startTime, LocalTime endTime, String teacherName) {

}