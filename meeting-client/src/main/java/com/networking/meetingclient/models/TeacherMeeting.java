package com.networking.meetingclient.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalTime;

@AllArgsConstructor
@Data
public class TeacherMeeting {
    public String teacherName;
    public String meetingType;
    public int remainingParticipants;
    public LocalTime startTime;
    public LocalTime endTime;
    // constructors, getters and setters
}
