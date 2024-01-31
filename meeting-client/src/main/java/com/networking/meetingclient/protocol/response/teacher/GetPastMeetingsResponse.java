package com.networking.meetingclient.protocol.response.teacher;

import com.networking.meetingclient.models.TeacherMeeting;
import com.networking.meetingclient.protocol.response.PagingResponse;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString(callSuper = true)
@Getter
@Setter
@NoArgsConstructor
public class GetPastMeetingsResponse extends PagingResponse<TeacherMeeting> {
}
