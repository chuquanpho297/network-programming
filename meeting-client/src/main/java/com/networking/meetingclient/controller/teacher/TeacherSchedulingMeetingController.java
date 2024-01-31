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
import javafx.scene.control.Button;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TableCell;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class TeacherSchedulingMeetingController extends Controller implements Initializable {
    @FXML
    private PaginationController paginationController;
    @FXML
    private TableViewTeacherController tableViewTeacherController;

    private TeacherSearchController teacherSearchController;

    public TeacherSchedulingMeetingController(ProgressIndicator progressIndicator) {
        this.progressIndicator = progressIndicator;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        tableViewTeacherController.getActionColumn().setCellFactory(param -> new TableCell<>() {
            private final Button moreBtn = new Button("Edit");
            private final HBox buttonGroup = new HBox(10); // 10 is the spacing between buttons


            {
                buttonGroup.getChildren().add(moreBtn);
                moreBtn.setOnAction(event -> {
                    TeacherMeeting selectedMeeting = tableViewTeacherController.getTableViewTeacher().getItems().get(getIndex());
                    showEditMeetingForm(selectedMeeting);
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

    public void showEditMeetingForm(TeacherMeeting meeting) {
        // Create a new Stage or Scene
        try {
            FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("teacher_main/edit_meeting.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Edit Meeting");
            loader.setController(new EditMeetingController(stage, meeting));
            // Set the scene and show the stage
            Scene scene = new Scene(loader.load()); // Set appropriate size
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
