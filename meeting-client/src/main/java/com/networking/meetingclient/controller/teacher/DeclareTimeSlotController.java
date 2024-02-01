package com.networking.meetingclient.controller.teacher;

import com.networking.meetingclient.controller.Controller;
import com.networking.meetingclient.service.TeacherMeetingService;
import com.networking.meetingclient.util.TimeMeetingEnum;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.util.Callback;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class DeclareTimeSlotController extends Controller implements Initializable {
    public TextField contentField;
    public ComboBox<String> meetingTypeField;
    public TextField participantNumberField;
    public DatePicker dateField;
    public ComboBox<String> startTimeField;
    public ComboBox<String> endTimeField;
    public Button saveButton;

    private TeacherMeetingService teacherMeetingService = TeacherMeetingService.getInstance();

    DeclareTimeSlotController(ProgressIndicator progressIndicator) {
        this.progressIndicator = progressIndicator;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> timeOptions = FXCollections.observableArrayList();

        TimeMeetingEnum[] times = TimeMeetingEnum.values();

        for (TimeMeetingEnum time : times) {
            timeOptions.add(time.toString());
        }

        startTimeField.setItems(timeOptions);
        endTimeField.setItems(timeOptions);

        meetingTypeField.setValue("Select Options");
        meetingTypeField.setCellFactory(new Callback<>() {
            @Override
            public ListCell<String> call(ListView<String> param) {
                return new ListCell<>() {
                    @Override
                    protected void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        setText(item);

                        if (item != null && item.equals("Select Options")) {
                            setDisable(true);
                        }
                    }
                };
            }
        });

        dateField.setDayCellFactory(new Callback<>() {
            @Override
            public DateCell call(final DatePicker datePicker) {
                return new DateCell() {
                    @Override
                    public void updateItem(LocalDate item, boolean empty) {
                        super.updateItem(item, empty);

                        if (item.isBefore(LocalDate.now())) {
                            setDisable(true);
                            setStyle("-fx-background-color: #ffc0cb;");
                        }
                    }
                };
            }
        });
        saveButton.setOnAction(event -> {
            teacherMeetingService.declareTimeSlot(
                    contentField.getText(),
                    meetingTypeField.getValue(),
                    participantNumberField.getText(),
                    startTimeField.getValue(),
                    endTimeField.getValue(),
                    dateField.getValue()
            );
            System.out.println("Declare Time Slot");
        });
    }
}
