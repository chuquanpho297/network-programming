package com.networking.meetingclient.protocol.response.student;

import com.networking.meetingclient.models.StudentMeeting;
import com.networking.meetingclient.protocol.response.PagingResponse;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString(callSuper = true)
@NoArgsConstructor
@Getter
@Setter
public class GetTimeSlotResponse extends PagingResponse<StudentMeeting> {
}
