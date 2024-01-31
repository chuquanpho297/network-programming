package com.networking.meetingclient.protocol.request.student;

import com.networking.meetingclient.protocol.request.Request;
import com.networking.meetingclient.util.student.StudentRequestEnum;
import lombok.*;

@ToString(callSuper = true)
@Getter
@Setter
@NoArgsConstructor
public class BookMeetingRequest extends Request {
    private Integer meetingId;

    @Builder
    public BookMeetingRequest(Integer meetingId) {
        super(StudentRequestEnum.BOOK_MEETING.getRequest());
        this.meetingId = meetingId;
    }
}
