package com.networking.meetingclient.protocol.request.student;

import com.networking.meetingclient.protocol.request.PagingRequest;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@ToString(callSuper = true)
@Getter
@Setter
@NoArgsConstructor
public class StudentMeetingRequest extends PagingRequest {

    private String teacherSearch;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalTime startTime;
    private LocalTime endTime;

    public StudentMeetingRequest(@NonNull String action, String teacherSearch, LocalDate startDate, LocalDate endDate, LocalTime startTime, LocalTime endTime, Integer page, Integer size) {
        super(action, page, size);
        this.teacherSearch = teacherSearch != null ? teacherSearch : "";
        this.startDate = startDate;
        this.endDate = endDate;
        this.startTime = startTime;
        this.endTime = endTime;
    }
}
