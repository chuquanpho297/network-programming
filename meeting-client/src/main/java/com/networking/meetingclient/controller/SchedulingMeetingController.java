package com.networking.meetingclient.controller;

import com.networking.meetingclient.models.TeacherMeeting;
import com.networking.meetingclient.seed.MeetingSeed;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.time.LocalTime;
import java.util.ResourceBundle;

public class SchedulingMeetingController extends Controller implements Initializable {

    private final ProgressIndicator progressIndicator;
    @FXML
    private VBox body;
    @FXML
    private DatePicker end_date;
    @FXML
    private DatePicker start_date;
    @FXML
    private Button searchBtn;
    @FXML
    private Pagination pagination;
    @FXML
    private TableView<TeacherMeeting> tableView;
    @FXML
    private TableColumn<TeacherMeeting, String> teacherNameColumn;
    @FXML
    private TableColumn<TeacherMeeting, String> meetingTypeColumn;
    @FXML
    private TableColumn<TeacherMeeting, Integer> remainingParticipantsColumn;
    @FXML
    private TableColumn<TeacherMeeting, LocalTime> startTimeColumn;
    @FXML
    private TableColumn<TeacherMeeting, LocalTime> endTimeColumn;
    @FXML
    private TableColumn<TeacherMeeting, Void> actionColumn;

    public SchedulingMeetingController(ProgressIndicator progressIndicator) {
        this.progressIndicator = progressIndicator;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        searchBtn.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            System.out.println("start_date: " + start_date.getValue());
            System.out.println("end_date: " + end_date.getValue());
        });

        teacherNameColumn.setCellValueFactory(new PropertyValueFactory<>("teacherName"));
        meetingTypeColumn.setCellValueFactory(new PropertyValueFactory<>("meetingType"));
        remainingParticipantsColumn.setCellValueFactory(new PropertyValueFactory<>("remainingParticipants"));
        startTimeColumn.setCellValueFactory(new PropertyValueFactory<>("startTime"));
        endTimeColumn.setCellValueFactory(new PropertyValueFactory<>("endTime"));
        actionColumn.setCellFactory(param -> new TableCell<TeacherMeeting, Void>() {
            private final Button btn = new Button("Cancel");
            private final StackPane paddedButton = new StackPane();

            {
                paddedButton.getChildren().add(btn);
                btn.setOnAction(event -> {
//                    TeacherMeeting teacherMeeting = getTableView().getItems().get(getIndex());
                    System.out.println("Button clicked for " + getIndex());
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(paddedButton);
                }
            }
        });

        Task<ObservableList<TeacherMeeting>> loadDataTask = getObservableListTask();

        new Thread(loadDataTask).start();

        pagination.setPageCount(10);

        pagination.currentPageIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                System.out.println(newValue);
                tableView.getItems().clear();
                Task<ObservableList<TeacherMeeting>> loadDataTask = getObservableListTask();
                new Thread(loadDataTask).start();
            }
        });
    }

    public Task<ObservableList<TeacherMeeting>> getObservableListTask() {
        Task<ObservableList<TeacherMeeting>> loadDataTask = new Task<>() {
            @Override
            protected ObservableList<TeacherMeeting> call() {
                return FXCollections.observableArrayList(MeetingSeed.createRandomDataList());
            }
        };

        loadDataTask.setOnRunning((e) -> progressIndicator.setVisible(true));
        loadDataTask.setOnSucceeded((e) -> {
            progressIndicator.setVisible(false);
            tableView.setItems(loadDataTask.getValue());
        });
        loadDataTask.setOnFailed((e) -> progressIndicator.setVisible(false));
        return loadDataTask;
    }
}
