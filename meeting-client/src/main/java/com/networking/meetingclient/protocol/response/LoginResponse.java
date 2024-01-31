package com.networking.meetingclient.protocol.response;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@NoArgsConstructor
@ToString(callSuper = true)
public class LoginResponse extends OkResponse {
    private String session;
    private String role;
    private String fullname;
}
