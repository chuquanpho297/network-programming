package com.networking.meetingclient.service;

import com.networking.meetingclient.StateManager;
import com.networking.meetingclient.protocol.request.student.BookMeetingRequest;
import com.networking.meetingclient.protocol.request.student.CancelMeetingRequest;
import com.networking.meetingclient.protocol.request.student.GetScheduledStudentMeetingRequest;
import com.networking.meetingclient.protocol.request.student.GetTimeSlotRequest;
import com.networking.meetingclient.util.JacksonUtil;
import com.networking.meetingclient.util.JavaFxUtil;
import com.networking.meetingclient.util.TimeMeetingEnum;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

public class StudentMeetingService extends Service {

    private static StudentMeetingService studentMeetingService;

    public static StudentMeetingService getInstance() {
        if (Objects.isNull(studentMeetingService)) {
            studentMeetingService = new StudentMeetingService();
        }
        return studentMeetingService;
    }

    public void getTimeSlots(LocalDate startDateField, LocalDate endDateField, String startTimeField, String endTimeField, String teacherSearchField, Integer pageField, Integer sizeField) {

        LocalTime startTime = TimeMeetingEnum.fromTimeMeetingToLocalTime(startTimeField);
        LocalTime endTime = TimeMeetingEnum.fromTimeMeetingToLocalTime(endTimeField);

        if (Objects.isNull(startDateField)) {
            startDateField = LocalDate.now();
        }

        if (!Objects.isNull(endDateField) && startDateField.isAfter(endDateField)) {
            JavaFxUtil.createAlert("Error Dialog", "Input Error", "Start date must be before end date");
            return;
        }

        if (!Objects.isNull(endDateField) && startDateField.isEqual(endDateField) && !startTime.isBefore(endTime)) {
            JavaFxUtil.createAlert("Error Dialog", "Input Error", "Start time must be before end time");
            return;
        }


        GetTimeSlotRequest getTimeSlotRequest = GetTimeSlotRequest.builder()
                .teacherSearch(teacherSearchField)
                .startDate(startDateField)
                .endDate(endDateField)
                .startTime(startTime)
                .endTime(endTime)
                .page(pageField)
                .size(sizeField)
                .build();

        try {
            String json = JacksonUtil.getInstance().writeValueAsString(getTimeSlotRequest);
            String receiveMess = StateManager.getInstance().getClientSocket().sendAndReceive(json);
            System.out.println("Receive: " + receiveMess);
            receiveMess = handleResponse(receiveMess);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getScheduledMeetings(LocalDate startDateField, LocalDate endDateField, String startTimeField, String endTimeField, String teacherSearchField, Integer pageField, Integer sizeField) {

        LocalTime startTime = TimeMeetingEnum.fromTimeMeetingToLocalTime(startTimeField);
        LocalTime endTime = TimeMeetingEnum.fromTimeMeetingToLocalTime(endTimeField);

        GetScheduledStudentMeetingRequest getScheduledStudentMeetingRequest = GetScheduledStudentMeetingRequest.builder()
                .teacherSearch(teacherSearchField)
                .startDate(startDateField)
                .endDate(endDateField)
                .startTime(startTime)
                .endTime(endTime)
                .page(pageField)
                .size(sizeField)
                .build();

        try {
            String json = JacksonUtil.getInstance().writeValueAsString(getScheduledStudentMeetingRequest);
            String receiveMess = StateManager.getInstance().getClientSocket().sendAndReceive(json);
            System.out.println("Receive: " + receiveMess);
            receiveMess = handleResponse(receiveMess);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void cancelMeeting(Integer meetingId) {
        CancelMeetingRequest cancelMeetingRequest = CancelMeetingRequest.builder()
                .meetingId(meetingId)
                .build();

        try {
            String json = JacksonUtil.getInstance().writeValueAsString(cancelMeetingRequest);
            String receiveMess = StateManager.getInstance().getClientSocket().sendAndReceive(json);
            System.out.println("Receive: " + receiveMess);
            receiveMess = handleResponse(receiveMess);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void bookMeeting(Integer meetingId) {
        BookMeetingRequest bookMeetingRequest = BookMeetingRequest.builder()
                .meetingId(meetingId)
                .build();

        try {
            String json = JacksonUtil.getInstance().writeValueAsString(bookMeetingRequest);
            String receiveMess = StateManager.getInstance().getClientSocket().sendAndReceive(json);
            System.out.println("Receive: " + receiveMess);
            receiveMess = handleResponse(receiveMess);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
