package com.networking.meetingclient.protocol.request;

import com.networking.meetingclient.util.RequestEnum;
import lombok.*;

@ToString(callSuper = true)
@Getter
@Setter
@NoArgsConstructor
public class LoginRequest extends Request {
    private String username;
    private String password;

    @Builder
    public LoginRequest(String username, String password) {
        super(RequestEnum.LOGIN.getRequest());
        this.username = username;
        this.password = password;
    }
}
