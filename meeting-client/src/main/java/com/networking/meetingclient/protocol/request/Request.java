package com.networking.meetingclient.protocol.request;

import com.networking.meetingclient.StateManager;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class Request {
    private String action;
    private String session = StateManager.getInstance().getUser() != null ? StateManager.getInstance().getUser().getSession() : null;

    public Request(String action) {
        this.action = action;
    }


}