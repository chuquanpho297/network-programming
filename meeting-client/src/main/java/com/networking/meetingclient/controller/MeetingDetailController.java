package com.networking.meetingclient.controller;

import com.networking.meetingclient.models.Meeting;
import com.networking.meetingclient.models.Student;
import javafx.collections.FXCollections;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.ResourceBundle;

public class MeetingDetailController implements Initializable {

    public TableColumn<Student, Integer> studentIdColumn;
    public TableColumn<Student, String> fullNameColumn;
    public TableView<Student> tableViewStudent;
    public GridPane infoGrid;
    private final Meeting meeting;

    public MeetingDetailController(Meeting meeting) {
        this.meeting = meeting;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        studentIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        fullNameColumn.setCellValueFactory(new PropertyValueFactory<>("fullname"));
        tableViewStudent.setItems(FXCollections.observableArrayList(
                meeting.getParticipants()
        ));
        infoGrid.add(new Label(meeting.getContent()), 1, 0);
        infoGrid.add(new Label(meeting.getMeetingType()), 1, 1);
        infoGrid.add(new Label(meeting.getRemainingParticipants().toString()), 1, 2);
        infoGrid.add(new Label(meeting.getParticipantsNumber().toString()), 1, 3);
        infoGrid.add(new Label(meeting.getStartTime().toString() + "-" + meeting.getEndTime().toString()), 1, 4);
        infoGrid.add(new Label(meeting.getDate().toString()), 1, 5);
        infoGrid.add(new Label(meeting.getWeek().toString()), 1, 6);
    }
}
