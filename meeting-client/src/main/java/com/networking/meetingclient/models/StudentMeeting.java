package com.networking.meetingclient.models;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;


@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
public class StudentMeeting extends Meeting {
    private String teacherName;

    @Builder
    public StudentMeeting(Integer id, String content, String meetingType, Integer remainingParticipants, Integer participantsNumber, LocalTime startTime, LocalTime endTime, String teacherName, LocalDate day, Integer week) {
        super(id, content, meetingType, remainingParticipants, startTime, endTime, day, week, participantsNumber);
        this.teacherName = teacherName;
    }
}
