package com.networking.meetingclient.util;

import lombok.Getter;

@Getter
public enum MeetingOptionsEnum {
    SCHEDULED_MEETING("Scheduled"),
    CANCELLED_MEETING("Unscheduled"),
    ALL_MEETING("All");

    private final String meetingOption;

    MeetingOptionsEnum(String meetingOption) {
        this.meetingOption = meetingOption;
    }

}
