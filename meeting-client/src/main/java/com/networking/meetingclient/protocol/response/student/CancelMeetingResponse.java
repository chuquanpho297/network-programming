package com.networking.meetingclient.protocol.response.student;

import com.networking.meetingclient.protocol.response.OkResponse;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString(callSuper = true)
@Getter
@Setter
@NoArgsConstructor
public class CancelMeetingResponse extends OkResponse {
}
