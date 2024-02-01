package com.networking.meetingclient.controller.student;

import com.networking.meetingclient.models.StudentMeeting;
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

@Getter
public class TableViewStudentController implements Initializable {
    @FXML
    private TableView<StudentMeeting> tableViewStudent;
    @FXML
    private TableColumn<StudentMeeting, String> teacherNameColumn;
    @FXML
    private TableColumn<StudentMeeting, String> meetingTypeColumn;
    @FXML
    private TableColumn<StudentMeeting, Integer> remainingParticipantsColumn;
    @FXML
    private TableColumn<StudentMeeting, Integer> weekColumn;
    @FXML
    private TableColumn<StudentMeeting, LocalDate> dayColumn;
    @FXML
    private TableColumn<StudentMeeting, LocalTime> startTimeColumn;
    @FXML
    private TableColumn<StudentMeeting, LocalTime> endTimeColumn;
    @FXML
    private TableColumn<StudentMeeting, String> contentColumn;

    @FXML
    private TableColumn<StudentMeeting, Void> actionColumn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        teacherNameColumn.setCellValueFactory(new PropertyValueFactory<>("teacherName"));
        contentColumn.setCellValueFactory(new PropertyValueFactory<>("content"));
        meetingTypeColumn.setCellValueFactory(new PropertyValueFactory<>("meetingType"));
        remainingParticipantsColumn.setCellValueFactory(new PropertyValueFactory<>("remainingParticipants"));
        dayColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        weekColumn.setCellValueFactory(new PropertyValueFactory<>("week"));
        startTimeColumn.setCellValueFactory(new PropertyValueFactory<>("startTime"));
        endTimeColumn.setCellValueFactory(new PropertyValueFactory<>("endTime"));
    }

    public void addTableViewData(ObservableList<StudentMeeting> studentMeetingObservableList) {
        tableViewStudent.setItems(studentMeetingObservableList);
    }

    public void clearTableViewData() {
        tableViewStudent.getItems().clear();
    }

}
