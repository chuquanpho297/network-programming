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
import javafx.scene.layout.StackPane;

import java.net.URL;
import java.time.LocalTime;
import java.util.ResourceBundle;

import static javafx.scene.input.MouseEvent.MOUSE_CLICKED;

public class TimeSlotController extends Controller implements Initializable {

    private final static int pageCount = 10;
    private final ProgressIndicator progressIndicator;
    @FXML
    private Pagination pagination;
    @FXML
    private TextField searchField;
    @FXML
    private Button searchButton;
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

    public TimeSlotController(ProgressIndicator progressIndicator) {
        this.progressIndicator = progressIndicator;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        teacherNameColumn.setCellValueFactory(new PropertyValueFactory<>("teacherName"));
        meetingTypeColumn.setCellValueFactory(new PropertyValueFactory<>("meetingType"));
        remainingParticipantsColumn.setCellValueFactory(new PropertyValueFactory<>("remainingParticipants"));
        startTimeColumn.setCellValueFactory(new PropertyValueFactory<>("startTime"));
        endTimeColumn.setCellValueFactory(new PropertyValueFactory<>("endTime"));
        actionColumn.setCellFactory(param -> new TableCell<TeacherMeeting, Void>() {
            private final Button btn = new Button("Book");
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

        this.searchButton.addEventHandler(MOUSE_CLICKED, event -> {
            System.out.println("searchField: " + searchField.getText());
        });

        pagination.setPageCount(pageCount);

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


