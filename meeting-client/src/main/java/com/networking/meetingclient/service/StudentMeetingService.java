package com.networking.meetingclient.service;

import com.networking.meetingclient.StateManager;
import com.networking.meetingclient.protocol.request.student.BookMeetingRequest;
import com.networking.meetingclient.protocol.request.student.CancelMeetingRequest;
import com.networking.meetingclient.protocol.request.student.GetScheduledStudentMeetingRequest;
import com.networking.meetingclient.protocol.request.student.GetTimeSlotRequest;
import com.networking.meetingclient.util.JacksonUtil;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

public class StudentMeetingService {

    private static StudentMeetingService studentMeetingService;

    public static StudentMeetingService getInstance() {
        if (Objects.isNull(studentMeetingService)) {
            studentMeetingService = new StudentMeetingService();
        }
        return studentMeetingService;
    }

    public void getTimeSlots(LocalDate startDateField, LocalDate endDateField, LocalTime startTimeField, LocalTime endTimeField, String teacherSearchField, Integer pageField, Integer sizeField) {
        GetTimeSlotRequest getTimeSlotRequest = GetTimeSlotRequest.builder()
                .teacherSearch(teacherSearchField)
                .startDate(startDateField)
                .endDate(endDateField)
                .startTime(startTimeField)
                .endTime(endTimeField)
                .page(pageField)
                .size(sizeField)
                .build();

        try {
            String json = JacksonUtil.getInstance().writeValueAsString(getTimeSlotRequest);
            String receiveMess = StateManager.getInstance().getClientSocket().sendAndReceive(json);
            System.out.println("Receive: " + receiveMess);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getScheduledMeetings(LocalDate startDateField, LocalDate endDateField, LocalTime startTimeField, LocalTime endTimeField, String teacherSearchField, Integer pageField, Integer sizeField) {
        GetScheduledStudentMeetingRequest getScheduledStudentMeetingRequest = GetScheduledStudentMeetingRequest.builder()
                .teacherSearch(teacherSearchField)
                .startDate(startDateField)
                .endDate(endDateField)
                .startTime(startTimeField)
                .endTime(endTimeField)
                .page(pageField)
                .size(sizeField)
                .build();

        try {
            String json = JacksonUtil.getInstance().writeValueAsString(getScheduledStudentMeetingRequest);
            String receiveMess = StateManager.getInstance().getClientSocket().sendAndReceive(json);
            System.out.println("Receive: " + receiveMess);
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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
