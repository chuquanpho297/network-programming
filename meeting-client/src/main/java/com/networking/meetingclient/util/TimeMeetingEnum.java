package com.networking.meetingclient.util;


import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public enum TimeMeetingEnum {
    HOUR_07("07:00"),
    HOUR_08("08:00"),
    HOUR_09("09:00"),
    HOUR_10("10:00"),
    HOUR_11("11:00"),
    HOUR_12("12:00"),
    HOUR_13("13:00"),
    HOUR_14("14:00"),
    HOUR_15("15:00"),
    HOUR_16("16:00"),
    HOUR_17("17:00");

    private final String timeMeeting;

    TimeMeetingEnum(String timeMeeting) {
        this.timeMeeting = timeMeeting;
    }

    public static String fromLocalTimeToString(LocalTime localTime) {
        String timeString = localTime.format(DateTimeFormatter.ofPattern("HH:mm"));
        for (TimeMeetingEnum time : TimeMeetingEnum.values()) {
            if (time.timeMeeting.equals(timeString)) {
                return time.toString();
            }
        }
        throw new IllegalArgumentException("Invalid time: " + timeString);
    }

    public static LocalTime fromTimeMeetingToLocalTime(String timeMeeting) {
        if (timeMeeting == null) {
            return null;
        }
        return LocalTime.parse(timeMeeting, DateTimeFormatter.ofPattern("HH:mm"));
    }

    @Override
    public String toString() {
        return this.timeMeeting;
    }
}