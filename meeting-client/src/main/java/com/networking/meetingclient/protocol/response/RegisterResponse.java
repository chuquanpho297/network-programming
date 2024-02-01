package com.networking.meetingclient.protocol.response;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@NoArgsConstructor
@ToString(callSuper = true)
public class RegisterResponse extends Response {
    private String session;
    private String role;
    private String fullname;
}
