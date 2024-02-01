package com.networking.meetingclient.models;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;


@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
public class StudentMeeting extends Meeting {
    private String teacherName;

    @Builder
    public StudentMeeting(Integer id, String content, String meetingType, Integer remainingParticipants, Integer participantsNumber, LocalTime startTime, LocalTime endTime, String teacherName, LocalDate date, Integer week, List<Student> participants) {
        super(id, content, meetingType, remainingParticipants, startTime, endTime, date, week, participantsNumber, participants);
        this.teacherName = teacherName;
    }
}
