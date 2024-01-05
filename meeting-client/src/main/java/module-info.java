module com.networking.meetingclient {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.logging;
    requires static lombok;
    requires modelmapper;
    requires com.fasterxml.jackson.annotation;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;
    opens com.networking.meetingclient.protocol to com.fasterxml.jackson.databind;
    opens com.networking.meetingclient to javafx.fxml;
    exports com.networking.meetingclient;
    exports com.networking.meetingclient.controller;
    opens com.networking.meetingclient.controller to javafx.fxml;
    exports com.networking.meetingclient.models;
    opens com.networking.meetingclient.models to javafx.fxml;

}