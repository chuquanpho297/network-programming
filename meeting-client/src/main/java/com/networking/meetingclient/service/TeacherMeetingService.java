package com.networking.meetingclient.service;

import com.networking.meetingclient.StateManager;
import com.networking.meetingclient.protocol.request.teacher.DeclareTimeSlotRequest;
import com.networking.meetingclient.protocol.request.teacher.EditMeetingRequest;
import com.networking.meetingclient.protocol.request.teacher.GetMeetingsRequest;
import com.networking.meetingclient.protocol.request.teacher.GetPastMeetingsRequest;
import com.networking.meetingclient.util.JacksonUtil;
import com.networking.meetingclient.util.JavaFxUtil;
import com.networking.meetingclient.util.TimeMeetingEnum;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

public class TeacherMeetingService extends Service {
    private static TeacherMeetingService teacherMeetingService;


    public static TeacherMeetingService getInstance() {
        if (Objects.isNull(teacherMeetingService)) {
            teacherMeetingService = new TeacherMeetingService();
        }
        return teacherMeetingService;
    }

    public void declareTimeSlot(String content, String meetingType, String participantsNumberField, String startTimeField, String endTimeField, LocalDate day) {
        Integer participantsNumber;
        try {
            participantsNumber = Integer.parseInt(participantsNumberField);

        } catch (NumberFormatException | NullPointerException e) {
            JavaFxUtil.createAlert("Error Dialog", "Input Error", "Please enter a valid number for participants");
            return;
        }

        if (participantsNumber <= 0) {
            JavaFxUtil.createAlert("Error Dialog", "Input Error", "Please enter a valid number for participants");
            return;
        }

        if (content.isEmpty()) {
            JavaFxUtil.createAlert("Error Dialog", "Input Error", "Please enter a valid content");
            return;
        }

        LocalTime startTime = TimeMeetingEnum.fromTimeMeetingToLocalTime(startTimeField);
        LocalTime endTime = TimeMeetingEnum.fromTimeMeetingToLocalTime(endTimeField);

        DeclareTimeSlotRequest declareTimeSlotRequest = DeclareTimeSlotRequest.builder()
                .content(content)
                .meetingType(meetingType)
                .participantsNumber(participantsNumber)
                .startTime(startTime)
                .endTime(endTime)
                .day(day)
                .build();
        try {
            String json = JacksonUtil.getInstance().writeValueAsString(declareTimeSlotRequest);
            String receiveMess = StateManager.getInstance().getClientSocket().sendAndReceive(json);
            System.out.println("Receive: " + receiveMess);
            receiveMess = handleResponse(receiveMess);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void editMeeting(Integer meetingId, String content, String meetingType, String
            participantsNumberField, String startTimeField, String endTimeField, LocalDate day) {
        Integer participantsNumber;
        try {
            participantsNumber = Integer.parseInt(participantsNumberField);

        } catch (NumberFormatException | NullPointerException e) {
            JavaFxUtil.createAlert("Error Dialog", "Input Error", "Please enter a valid number for participants");
            return;
        }

        if (participantsNumber <= 0) {
            JavaFxUtil.createAlert("Error Dialog", "Input Error", "Please enter a valid number for participants");
            return;
        }

        if (content.isEmpty()) {
            JavaFxUtil.createAlert("Error Dialog", "Input Error", "Please enter a valid content");
            return;
        }
        LocalTime startTime = TimeMeetingEnum.fromTimeMeetingToLocalTime(startTimeField);
        LocalTime endTime = TimeMeetingEnum.fromTimeMeetingToLocalTime(endTimeField);

        EditMeetingRequest editMeetingRequest = EditMeetingRequest.builder()
                .meetingId(meetingId)
                .content(content)
                .meetingType(meetingType)
                .participantsNumber(participantsNumber)
                .startTime(startTime)
                .endTime(endTime)
                .day(day)
                .build();
        try {
            String json = JacksonUtil.getInstance().writeValueAsString(editMeetingRequest);
            String receiveMess = StateManager.getInstance().getClientSocket().sendAndReceive(json);
            System.out.println("Receive: " + receiveMess);
            receiveMess = handleResponse(receiveMess);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getMeetings(LocalDate startDate, LocalDate endDate, String startTimeField, String
            endTimeField, Integer page, Integer size) {
        LocalTime startTime = TimeMeetingEnum.fromTimeMeetingToLocalTime(startTimeField);
        LocalTime endTime = TimeMeetingEnum.fromTimeMeetingToLocalTime(endTimeField);

        GetMeetingsRequest getMeetingsRequest = GetMeetingsRequest.builder()
                .startDate(startDate)
                .endDate(endDate)
                .startTime(startTime)
                .endTime(endTime)
                .page(page)
                .size(size)
                .build();

        try {
            String json = JacksonUtil.getInstance().writeValueAsString(getMeetingsRequest);
            String receiveMess = StateManager.getInstance().getClientSocket().sendAndReceive(json);
            System.out.println("Receive: " + receiveMess);
            receiveMess = handleResponse(receiveMess);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getPastMeeting(LocalDate startDate, LocalDate endDate, String startTimeField, String
            endTimeField, Integer page, Integer size) {
        LocalTime startTime = TimeMeetingEnum.fromTimeMeetingToLocalTime(startTimeField);
        LocalTime endTime = TimeMeetingEnum.fromTimeMeetingToLocalTime(endTimeField);

        GetPastMeetingsRequest getPastMeetingsRequest = GetPastMeetingsRequest.builder()
                .startDate(startDate)
                .endDate(endDate)
                .startTime(startTime)
                .endTime(endTime)
                .page(page)
                .size(size)
                .build();

        try {
            String json = JacksonUtil.getInstance().writeValueAsString(getPastMeetingsRequest);
            String receiveMess = StateManager.getInstance().getClientSocket().sendAndReceive(json);
            System.out.println("Receive: " + receiveMess);
            receiveMess = handleResponse(receiveMess);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
