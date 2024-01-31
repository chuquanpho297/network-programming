package com.networking.meetingclient.protocol.request.student;

import com.networking.meetingclient.protocol.request.Request;
import com.networking.meetingclient.util.student.StudentRequestEnum;
import lombok.*;

@ToString(callSuper = true)
@Getter
@Setter
@NoArgsConstructor
public class CancelMeetingRequest extends Request {

    private Integer meetingId;

    @Builder
    public CancelMeetingRequest(Integer meetingId) {
        super(StudentRequestEnum.CANCEL_MEETING.getRequest());
        this.meetingId = meetingId;
    }
}
