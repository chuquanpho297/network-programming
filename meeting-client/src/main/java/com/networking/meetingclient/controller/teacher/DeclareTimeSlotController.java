package com.networking.meetingclient.controller.teacher;

import com.networking.meetingclient.controller.Controller;
import com.networking.meetingclient.models.TeacherMeeting;
import com.networking.meetingclient.util.TimeMeetingEnum;
import com.networking.meetingclient.util.WeekUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.util.Callback;

import java.net.URL;
import java.util.ResourceBundle;

public class DeclareTimeSlotController extends Controller implements Initializable {
    public TextField contentField;
    public ComboBox<String> meetingTypeField;
    public TextField participantNumberField;
    public DatePicker dateField;
    public ComboBox<String> startTimeField;
    public ComboBox<String> endTimeField;
    public Button saveButton;

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

        saveButton.setOnAction(event -> {
            // Update the meeting object with the new values
            TeacherMeeting editMeeting = TeacherMeeting.builder()
                    .content(contentField.getText())
                    .meetingType(meetingTypeField.getValue())
                    .remainingParticipants(Integer.parseInt(participantNumberField.getText()))
                    .day(dateField.getValue())
                    .week(WeekUtil.getWeek(dateField.getValue()))
                    .startTime(TimeMeetingEnum.fromTimeMeetingToLocalTime(startTimeField.getValue()))
                    .endTime(TimeMeetingEnum.fromTimeMeetingToLocalTime(endTimeField.getValue()))
                    .build();

        });
    }
}
