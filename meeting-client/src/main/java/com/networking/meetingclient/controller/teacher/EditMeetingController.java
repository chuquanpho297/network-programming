package com.networking.meetingclient.controller.teacher;

import com.networking.meetingclient.models.TeacherMeeting;
import com.networking.meetingclient.service.TeacherMeetingService;
import com.networking.meetingclient.util.TimeMeetingEnum;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.net.URL;
import java.util.ResourceBundle;


public class EditMeetingController implements Initializable {
    private final Stage stage;
    private final TeacherMeeting meeting;
    public TextField contentField;
    public Button saveButton;
    public TextField participantNumberField;
    public ComboBox<String> meetingTypeField;
    public DatePicker dateField;
    public ComboBox<String> startTimeField;
    public ComboBox<String> endTimeField;

    private TeacherMeetingService teacherMeetingService = TeacherMeetingService.getInstance();

    public EditMeetingController(Stage stage, TeacherMeeting meeting) {
        this.stage = stage;
        this.meeting = meeting;
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

        contentField.setText(meeting.getContent());
        participantNumberField.setText(String.valueOf(meeting.getRemainingParticipants()));
        meetingTypeField.setValue(meeting.getMeetingType());
        dateField.setValue(meeting.getDate());
        startTimeField.setValue(TimeMeetingEnum.fromLocalTimeToString(meeting.getStartTime()));
        endTimeField.setValue(TimeMeetingEnum.fromLocalTimeToString(meeting.getEndTime()));
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
            teacherMeetingService.editMeeting(
                    meeting.getId(),
                    contentField.getText(),
                    meetingTypeField.getValue(),
                    participantNumberField.getText(),
                    startTimeField.getValue(),
                    endTimeField.getValue(),
                    dateField.getValue()
            );
            System.out.println("Edit Meeting");
            // Close the form
            stage.close();
        });
    }
}
