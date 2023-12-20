module com.networking.meetingclient {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.logging;
    requires static lombok;

    opens com.networking.meetingclient to javafx.fxml;
    exports com.networking.meetingclient;
    exports com.networking.meetingclient.controller;
    opens com.networking.meetingclient.controller to javafx.fxml;

}