package com.networking.meetingclient.seed;

import com.networking.meetingclient.models.Meeting;
import com.networking.meetingclient.models.StudentMeeting;
import com.networking.meetingclient.models.TeacherMeeting;
import com.networking.meetingclient.util.ModelUtil;
import com.networking.meetingclient.util.WeekUtil;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MeetingSeed<T extends Meeting> {
    private final Class<T> type;

    public MeetingSeed(Class<T> type) {
        this.type = type;
    }

    private static String generateRandomString(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuilder stringBuilder = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(characters.length());
            stringBuilder.append(characters.charAt(randomIndex));
        }

        return stringBuilder.toString();
    }

    public List<T> createRandomDataList() {
        List<T> meetings = new ArrayList<>();

        for (int i = 0; i < new Random().nextInt(10, 100); i++) {
            meetings.add(createRandomData());
        }
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return meetings;
    }

    private T createRandomData() {
        String teacherName = generateRandomString(10);
        String content = generateRandomString(10);
        String meetingType = ModelUtil.MeetingType.values()[new Random().nextInt(ModelUtil.MeetingType.values().length)].getType();
        int remainingParticipants = new Random().nextInt(10);
        LocalTime startTime = LocalTime.of(new Random().nextInt(7, 17), 0, 0);
        LocalTime endTime = LocalTime.of(new Random().nextInt(7, 17), 0, 0);
        LocalDate day = LocalDate.of(2023, 10, new Random().nextInt(27) + 1);
        if (type.equals(StudentMeeting.class)) {
            return type.cast(StudentMeeting.builder()
                    .id(new Random().nextInt(100))
                    .content(content)
                    .meetingType(meetingType)
                    .remainingParticipants(remainingParticipants)
                    .startTime(startTime)
                    .endTime(endTime)
                    .teacherName(teacherName)
                    .day(day)
                    .week(WeekUtil.getWeek(day))
                    .build());
        } else if (type.equals(TeacherMeeting.class)) {
            return type.cast(TeacherMeeting.builder()
                    .id(new Random().nextInt(100))
                    .content(content)
                    .meetingType(meetingType)
                    .remainingParticipants(remainingParticipants)
                    .startTime(startTime)
                    .endTime(endTime)
                    .day(day)
                    .week(WeekUtil.getWeek(day))
                    .build());
        } else {
            throw new RuntimeException("Unknown type");
        }
    }
}
