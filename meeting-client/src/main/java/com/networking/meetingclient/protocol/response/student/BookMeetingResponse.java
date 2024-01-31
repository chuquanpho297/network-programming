package com.networking.meetingclient.protocol.response.student;

import com.networking.meetingclient.protocol.request.Request;
import com.networking.meetingclient.protocol.response.OkResponse;
import com.networking.meetingclient.protocol.response.Response;
import com.networking.meetingclient.util.student.StudentRequestEnum;
import lombok.*;

@ToString(callSuper = true)
@Getter
@Setter
@NoArgsConstructor
public class BookMeetingResponse extends OkResponse {
}
