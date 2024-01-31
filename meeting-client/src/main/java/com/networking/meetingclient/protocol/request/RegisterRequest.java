package com.networking.meetingclient.protocol.request;

import com.networking.meetingclient.util.RequestEnum;
import lombok.*;

@ToString(callSuper = true)
@Setter
@Getter
@NoArgsConstructor
public class RegisterRequest extends Request {
    private String username;
    private String fullname;
    private String password;
    private String role;


    @Builder
    public RegisterRequest(String username, String fullname, String password, String role) {
        super(RequestEnum.REGISTER.getRequest());
        this.username = username;
        this.fullname = fullname;
        this.password = password;
        this.role = role;
    }
}
