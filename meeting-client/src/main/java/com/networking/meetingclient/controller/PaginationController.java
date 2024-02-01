package com.networking.meetingclient.controller;

import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Pagination;
import lombok.Getter;

import java.net.URL;
import java.util.ResourceBundle;

@Getter
public class PaginationController implements Initializable {

    @FXML
    private Pagination pagination;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        pagination.setPageCount(20);
    }

    public void setPaginationCount(int count) {
        pagination.setPageCount(count);
    }

    public void addPageChangeListener(ChangeListener<Number> changeListener) {
        pagination.currentPageIndexProperty().addListener(changeListener);
    }
}
