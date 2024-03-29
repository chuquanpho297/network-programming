package com.networking.meetingclient.protocol.request.teacher;

import com.networking.meetingclient.protocol.request.PagingRequest;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@ToString(callSuper = true)
@Getter
@Setter
@NoArgsConstructor
public class TeacherMeetingRequest extends PagingRequest {

    private LocalDate startDate;
    private LocalDate endDate;
    private LocalTime startTime;
    private LocalTime endTime;

    public TeacherMeetingRequest(@NonNull String action, LocalDate startDate, LocalDate endDate, LocalTime startTime, LocalTime endTime, Integer page, Integer size) {
        super(action, page, size);
        this.startDate = startDate;
        this.endDate = endDate;
        this.startTime = startTime;
        this.endTime = endTime;
    }
}
