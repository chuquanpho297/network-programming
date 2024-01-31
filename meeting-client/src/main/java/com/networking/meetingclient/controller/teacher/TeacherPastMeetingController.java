package com.networking.meetingclient.controller.teacher;

import com.networking.meetingclient.HelloApplication;
import com.networking.meetingclient.controller.Controller;
import com.networking.meetingclient.controller.PaginationController;
import com.networking.meetingclient.models.TeacherMeeting;
import com.networking.meetingclient.seed.MeetingSeed;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class TeacherPastMeetingController extends Controller implements Initializable {

    @FXML
    private PaginationController paginationController;
    @FXML
    private TableViewTeacherController tableViewTeacherController;
    @FXML
    private TeacherSearchController teacherSearchController;

    public TeacherPastMeetingController(ProgressIndicator progressIndicator) {
        this.progressIndicator = progressIndicator;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        teacherSearchController.getStartDateField().setDayCellFactory(new Callback<>() {
            @Override
            public DateCell call(final DatePicker datePicker) {
                return new DateCell() {
                    @Override
                    public void updateItem(LocalDate item, boolean empty) {
                        super.updateItem(item, empty);

                        if (item.isAfter(LocalDate.now())) {
                            setDisable(true);
                            setStyle("-fx-background-color: #ffc0cb;");
                        }
                    }
                };
            }
        });
        teacherSearchController.getEndDateField().setDayCellFactory(new Callback<>() {
            @Override
            public DateCell call(final DatePicker datePicker) {
                return new DateCell() {
                    @Override
                    public void updateItem(LocalDate item, boolean empty) {
                        super.updateItem(item, empty);

                        if (item.isAfter(LocalDate.now())) {
                            setDisable(true);
                            setStyle("-fx-background-color: #ffc0cb;");
                        }
                    }
                };
            }
        });
        tableViewTeacherController.getActionColumn().setCellFactory(param -> new TableCell<>() {
            private final Button moreBtn = new Button("More");
            private final HBox buttonGroup = new HBox(10); // 10 is the spacing between buttons


            {
                buttonGroup.getChildren().add(moreBtn);
                moreBtn.setOnAction(event -> {
                    TeacherMeeting selectedMeeting = tableViewTeacherController.getTableViewTeacher().getItems().get(getIndex());
                    showMeetingDetails(selectedMeeting);
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(buttonGroup);
                }
            }
        });

        Task<ObservableList<TeacherMeeting>> loadDataTask = getObservableListTask();

        new Thread(loadDataTask).start();

        paginationController.setPaginationCount(11);

        paginationController.addPageChangeListener((observable, oldValue, newValue) -> {
            System.out.println(newValue);
            tableViewTeacherController.clearTableViewData();
            Task<ObservableList<TeacherMeeting>> loadDataTask1 = getObservableListTask();
            new Thread(loadDataTask1).start();
        });
    }

    public Task<ObservableList<TeacherMeeting>> getObservableListTask() {
        Task<ObservableList<TeacherMeeting>> loadDataTask = new Task<>() {
            @Override
            protected ObservableList<TeacherMeeting> call() {
                return FXCollections.observableArrayList(new MeetingSeed<>(TeacherMeeting.class).createRandomDataList());
            }
        };

        loadDataTask.setOnRunning((e) -> progressIndicator.setVisible(true));
        loadDataTask.setOnSucceeded((e) -> {
            progressIndicator.setVisible(false);
            tableViewTeacherController.addTableViewData(loadDataTask.getValue());
        });
        loadDataTask.setOnFailed((e) -> progressIndicator.setVisible(false));
        return loadDataTask;
    }

    private void showMeetingDetails(TeacherMeeting meeting) {
        // Create a new Stage or Scene
        try {
            FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("teacher_main/edit_meeting.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Edit Meeting");
            // Set the scene and show the stage
            Scene scene = new Scene(loader.load()); // Set appropriate size
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
