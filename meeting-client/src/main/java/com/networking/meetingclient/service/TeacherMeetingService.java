package com.networking.meetingclient.service;

import java.util.Objects;

public class TeacherMeetingService {
    private static TeacherMeetingService teacherMeetingService;

    public static TeacherMeetingService getInstance() {
        if (Objects.isNull(teacherMeetingService)) {
            teacherMeetingService = new TeacherMeetingService();
        }
        return teacherMeetingService;
    }

    public void editMeeting(){

    }
}
