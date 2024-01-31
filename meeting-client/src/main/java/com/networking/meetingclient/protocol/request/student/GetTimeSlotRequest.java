package com.networking.meetingclient.protocol.request.student;

import com.networking.meetingclient.util.student.StudentRequestEnum;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@ToString(callSuper = true)
@NoArgsConstructor
@Getter
@Setter
public class GetTimeSlotRequest extends StudentMeetingRequest {

    @Builder
    public GetTimeSlotRequest(String teacherSearch, LocalDate startDate, LocalDate endDate, LocalTime startTime, LocalTime endTime, Integer page, Integer size) {
        super(StudentRequestEnum.VIEW_AVAILABLE_TIME_SLOTS.getRequest(), teacherSearch, startDate, endDate, startTime, endTime, page, size);
    }

}
