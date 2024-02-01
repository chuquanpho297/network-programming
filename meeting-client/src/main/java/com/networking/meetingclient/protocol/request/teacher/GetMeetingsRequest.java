package com.networking.meetingclient.protocol.request.teacher;

import com.networking.meetingclient.util.teacher.TeacherRequestEnum;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@ToString(callSuper = true)
@Getter
@Setter
@NoArgsConstructor
public class GetMeetingsRequest extends TeacherMeetingRequest {
    @Builder
    public GetMeetingsRequest(LocalDate startDate, LocalDate endDate, LocalTime startTime, LocalTime endTime, Integer page, Integer size) {
        super(TeacherRequestEnum.VIEW_MEETINGS.getRequest(), startDate, endDate, startTime, endTime, page, size);
    }
}
