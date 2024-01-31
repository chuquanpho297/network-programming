package com.networking.meetingclient.protocol.request.student;

import com.networking.meetingclient.util.RequestEnum;
import com.networking.meetingclient.util.student.StudentRequestEnum;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@ToString(callSuper = true)
@Getter
@Setter
@NoArgsConstructor
public class GetScheduledStudentMeetingRequest extends StudentMeetingRequest {

    @Builder
    public GetScheduledStudentMeetingRequest(String teacherSearch, LocalDate startDate, LocalDate endDate, LocalTime startTime, LocalTime endTime, Integer page, Integer size) {
        super(StudentRequestEnum.VIEW_WEEKLY_SCHEDULE_MEETINGS.getRequest(), teacherSearch, startDate, endDate, startTime, endTime, page, size);
    }
}
