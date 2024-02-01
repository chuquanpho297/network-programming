package com.networking.meetingclient.models;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;


@Getter
@ToString(callSuper = true)
public class TeacherMeeting extends Meeting {

    @Builder
    public TeacherMeeting(Integer id, String content, String meetingType, Integer remainingParticipants, Integer participantsNumber, LocalTime startTime, LocalTime endTime, LocalDate day, Integer week, List<Student> participants
    ) {
        super(id, content, meetingType, remainingParticipants, startTime, endTime, day, week, participantsNumber, participants);
    }
}
