package com.networking.meetingclient.protocol.request.teacher;

import com.networking.meetingclient.protocol.request.Request;
import com.networking.meetingclient.util.teacher.TeacherRequestEnum;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@ToString(callSuper = true)
@Getter
@Setter
@NoArgsConstructor
public class EditMeetingRequest extends Request {
    private Long meetingId;
    private String content;
    private String meetingType;
    private Integer participantsNumber;
    private LocalTime startTime;
    private LocalTime endTime;
    private LocalDate day;

    @Builder
    public EditMeetingRequest(Long meetingId, String content, String meetingType, Integer participantsNumber, LocalTime startTime, LocalTime endTime, LocalDate day) {
        super(TeacherRequestEnum.EDIT_MEETING.getRequest());
        this.meetingId = meetingId;
        this.content = content;
        this.meetingType = meetingType;
        this.participantsNumber = participantsNumber;
        this.startTime = startTime;
        this.endTime = endTime;
        this.day = day;
    }
}
