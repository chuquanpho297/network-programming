package com.networking.meetingclient.models;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

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
    private LocalDate day;
    private Integer week;
    private Integer participantsNumber;
}
