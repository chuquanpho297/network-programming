package com.networking.meetingclient.util.teacher;

import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public enum TeacherRequestEnum {
    EDIT_MEETING("EDIT_MEETING"),
    DECLARE_TIME_SLOTS("DECLARE_TIME_SLOTS"),
    VIEW_MEETINGS("VIEW_MEETINGS"),
    VIEW_PAST_MEETINGS("VIEW_PAST_MEETINGS");

    private final String request;
}