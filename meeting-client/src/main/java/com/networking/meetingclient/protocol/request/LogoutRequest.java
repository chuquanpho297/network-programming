package com.networking.meetingclient.protocol.request;

import com.networking.meetingclient.util.RequestEnum;
import lombok.Builder;
import lombok.ToString;

@ToString(callSuper = true)
public class LogoutRequest extends Request {

    @Builder
    public LogoutRequest() {
        super(RequestEnum.LOGOUT.getRequest());
    }
}
