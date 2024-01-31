package com.networking.meetingclient.controller.student;

import com.networking.meetingclient.controller.Controller;
import com.networking.meetingclient.controller.PaginationController;
import com.networking.meetingclient.models.StudentMeeting;
import com.networking.meetingclient.seed.MeetingSeed;
import com.networking.meetingclient.service.StudentMeetingService;
import com.networking.meetingclient.util.TimeMeetingEnum;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class TimeSlotController extends Controller implements Initializable {
    private final StudentMeetingService studentMeetingService = StudentMeetingService.getInstance();
    public StudentSearchController studentSearchController;
    @FXML
    private TableViewStudentController tableViewStudentController;
    @FXML
    private PaginationController paginationController;

    public TimeSlotController(ProgressIndicator progressIndicator) {
        this.progressIndicator = progressIndicator;
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tableViewStudentController.getActionColumn().setCellFactory(param -> new TableCell<>() {
            private final Button btn = new Button("Book");
            private final StackPane paddedButton = new StackPane();

            {
                paddedButton.getChildren().add(btn);
                btn.setOnAction(event -> {
                    StudentMeeting studentMeeting = tableViewStudentController.getTableViewStudent().getItems().get(getIndex());
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Confirmation Dialog");
                    alert.setHeaderText("Book Meeting");
                    alert.setContentText("Are you sure you want to book this meeting?");

                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.get() == ButtonType.OK) {
                        System.out.println("Meeting booked for " + studentMeeting.getId());
                        studentMeetingService.bookMeeting(studentMeeting.getId());
//                        tableViewStudentController.clearTableViewData();
//                        Task<ObservableList<StudentMeeting>> loadDataTask1 = getObservableListTask();

//                        new Thread(loadDataTask1).start();
                    } else {
                        System.out.println("Booking cancelled for " + studentMeeting.getId());
                    }
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

        Task<ObservableList<StudentMeeting>> loadDataTask = getObservableListTask();

        new Thread(loadDataTask).start();

        paginationController.setPaginationCount(11);

        paginationController.addPageChangeListener((observable, oldValue, newValue) -> {
            System.out.println(newValue);
            tableViewStudentController.clearTableViewData();
            Task<ObservableList<StudentMeeting>> loadDataTask1 = getObservableListTask();

            new Thread(loadDataTask1).start();
        });

        studentSearchController.getSearchBtn().addEventHandler(MouseEvent.MOUSE_CLICKED, event ->
        {
            studentMeetingService.getTimeSlots(
                    studentSearchController.getStartDateField().getValue(),
                    studentSearchController.getEndDateField().getValue(),
                    TimeMeetingEnum.fromTimeMeetingToLocalTime(studentSearchController.getStartTimeField().getValue()),
                    TimeMeetingEnum.fromTimeMeetingToLocalTime(studentSearchController.getEndTimeField().getValue()),
                    studentSearchController.getTeacherNameField().getText(),
                    paginationController.getPagination().getCurrentPageIndex() + 1,
                    studentSearchController.getSizeField().getValue()
            );
        });
    }

    public Task<ObservableList<StudentMeeting>> getObservableListTask() {
        Task<ObservableList<StudentMeeting>> loadDataTask = new Task<>() {
            @Override
            protected ObservableList<StudentMeeting> call() {
                return FXCollections.observableArrayList(
                        new MeetingSeed<>(StudentMeeting.class).createRandomDataList()
                );
            }
        };

        loadDataTask.setOnRunning((e) -> progressIndicator.setVisible(true));
        loadDataTask.setOnSucceeded((e) -> {
            progressIndicator.setVisible(false);
            tableViewStudentController.addTableViewData(loadDataTask.getValue());
        });
        loadDataTask.setOnFailed((e) -> progressIndicator.setVisible(false));
        return loadDataTask;
    }
}


