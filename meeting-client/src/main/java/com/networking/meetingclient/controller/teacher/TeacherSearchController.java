package com.networking.meetingclient.controller.teacher;

import com.networking.meetingclient.util.TimeMeetingEnum;
import com.networking.meetingclient.util.WeekUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import lombok.Getter;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

@Getter
public class TeacherSearchController implements Initializable {

    public ComboBox<Integer> sizeField;
    @FXML
    private Button searchBtn;
    @FXML
    private DatePicker startDateField;
    @FXML
    private DatePicker endDateField;

    @FXML
    private ComboBox<String> startTimeField;
    @FXML
    private ComboBox<String> endTimeField;
    @FXML
    private ComboBox<String> weekField;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        sizeField.getItems().addAll(20, 30, 50);
        sizeField.setValue(20);

        ObservableList<String> weekOptions = FXCollections.observableArrayList();
        weekOptions.addAll(WeekUtil.getWeeks());
        weekField.setItems(weekOptions);
        weekField.setOnAction(event -> {
            String selectedWeek = weekField.getValue();
            startDateField.setValue(WeekUtil.getStartDateOfWeek(selectedWeek));
            endDateField.setValue(WeekUtil.getEndDateOfWeek(selectedWeek));
        });
        startDateField.setOnMouseClicked(event -> {
            weekField.setValue(null);
        });

        startDateField.setDayCellFactory(new Callback<>() {
            @Override
            public DateCell call(final DatePicker datePicker) {
                return new DateCell() {
                    @Override
                    public void updateItem(LocalDate item, boolean empty) {
                        super.updateItem(item, empty);

                        if (item.isBefore(LocalDate.now())) {
                            setDisable(true);
                            setStyle("-fx-background-color: #ffc0cb;");
                        }
                    }
                };
            }
        });
        endDateField.setDayCellFactory(new Callback<>() {
            @Override
            public DateCell call(final DatePicker datePicker) {
                return new DateCell() {
                    @Override
                    public void updateItem(LocalDate item, boolean empty) {
                        super.updateItem(item, empty);

                        if (item.isBefore(LocalDate.now())) {
                            setDisable(true);
                            setStyle("-fx-background-color: #ffc0cb;");
                        }
                    }
                };
            }
        });

        endDateField.setOnMouseClicked(event ->
        {
            weekField.setValue(null);
        });

        searchBtn.addEventHandler(MouseEvent.MOUSE_CLICKED, event ->

        {
            System.out.println("startDateField: " + startDateField.getValue());
            System.out.println("endDateField: " + endDateField.getValue());
        });

        ObservableList<String> timeOptions = FXCollections.observableArrayList();

        TimeMeetingEnum[] times = TimeMeetingEnum.values();

        for (
                TimeMeetingEnum time : times) {
            timeOptions.add(time.toString());
        }

        startTimeField.setItems(timeOptions);
        endTimeField.setItems(timeOptions);


    }
}
