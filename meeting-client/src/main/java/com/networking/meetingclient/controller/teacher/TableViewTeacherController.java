package com.networking.meetingclient.controller.teacher;

import com.networking.meetingclient.models.TeacherMeeting;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lombok.Getter;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ResourceBundle;

public class TableViewTeacherController implements Initializable {

    @Getter
    @FXML
    private TableView<TeacherMeeting> tableViewTeacher;
    @FXML
    private TableColumn<TeacherMeeting, String> meetingTypeColumn;
    @FXML
    private TableColumn<TeacherMeeting, Integer> remainingParticipantsColumn;
    @FXML
    private TableColumn<TeacherMeeting, Integer> weekColumn;
    @FXML
    private TableColumn<TeacherMeeting, LocalDate> dayColumn;
    @FXML
    private TableColumn<TeacherMeeting, LocalTime> startTimeColumn;
    @FXML
    private TableColumn<TeacherMeeting, LocalTime> endTimeColumn;
    @FXML
    private TableColumn<TeacherMeeting, String> contentColumn;

    @Getter
    @FXML
    private TableColumn<TeacherMeeting, Void> actionColumn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        contentColumn.setCellValueFactory(new PropertyValueFactory<>("content"));
        meetingTypeColumn.setCellValueFactory(new PropertyValueFactory<>("meetingType"));
        remainingParticipantsColumn.setCellValueFactory(new PropertyValueFactory<>("remainingParticipants"));
        dayColumn.setCellValueFactory(new PropertyValueFactory<>("day"));
        weekColumn.setCellValueFactory(new PropertyValueFactory<>("week"));
        startTimeColumn.setCellValueFactory(new PropertyValueFactory<>("startTime"));
        endTimeColumn.setCellValueFactory(new PropertyValueFactory<>("endTime"));
    }

    public void addTableViewData(ObservableList<TeacherMeeting> studentMeetingObservableList) {
        tableViewTeacher.setItems(studentMeetingObservableList);
    }

    public void clearTableViewData() {
        tableViewTeacher.getItems().clear();
    }
}
