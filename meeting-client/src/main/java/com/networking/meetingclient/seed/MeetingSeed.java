package com.networking.meetingclient.seed;

import com.networking.meetingclient.models.TeacherMeeting;
import com.networking.meetingclient.util.ModelUtil;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MeetingSeed {
    public static List<TeacherMeeting> createRandomDataList() {
        List<TeacherMeeting> teacherMeetings = new ArrayList<>();

        for (int i = 0; i < new Random().nextInt(10, 10 * 10); i++) {
            teacherMeetings.add(createRandomData());
        }
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return teacherMeetings;

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

    private static TeacherMeeting createRandomData() {
        String teacherName = generateRandomString(10);
        String meetingType = ModelUtil.MeetingType.values()[new Random().nextInt(ModelUtil.MeetingType.values().length)].getType();
        int remainingParticipants = new Random().nextInt(10);
        LocalTime startTime = LocalTime.of(new Random().nextInt(24), new Random().nextInt(60), new Random().nextInt(60));
        LocalTime endTime = LocalTime.of(new Random().nextInt(24), new Random().nextInt(60), new Random().nextInt(60));
        return new TeacherMeeting(teacherName, meetingType, remainingParticipants, startTime, endTime);
    }
}
