package com.networking.meetingclient.protocol.request.teacher;

import com.networking.meetingclient.util.teacher.TeacherRequestEnum;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalTime;

@ToString(callSuper = true)
@Getter
@Setter
public class GetMeetingsRequest extends TeacherMeetingRequest {

    @Builder
    public GetMeetingsRequest(LocalDate startDate, LocalDate endDate, LocalTime startTime, LocalTime endTime, String meetingOption, Integer page, Integer size) {
        super(TeacherRequestEnum.VIEW_MEETINGS.getRequest(), startDate, endDate, startTime, endTime, meetingOption, page, size);
    }
}
