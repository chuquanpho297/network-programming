package com.networking.meetingclient.models;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@ToString
@NoArgsConstructor
public class Meeting {
    private Integer id;
    private String content;
    private String meetingType;
    private Integer remainingParticipants;
    private LocalTime startTime;
    private LocalTime endTime;
    private LocalDate date;
    private Integer week;
    private Integer participantsNumber;
    private List<Student> participants;
}
