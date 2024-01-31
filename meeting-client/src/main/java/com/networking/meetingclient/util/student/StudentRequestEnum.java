package com.networking.meetingclient.util.student;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StudentRequestEnum {
    BOOK_MEETING("BOOK_MEETING"),
    CANCEL_MEETING("CANCEL_MEETING"),
    VIEW_WEEKLY_SCHEDULE_MEETINGS("VIEW_WEEKLY_SCHEDULE_MEETINGS"),
    VIEW_AVAILABLE_TIME_SLOTS("VIEW_AVAILABLE_TIME_SLOTS"),
    ;

    private final String request;
}