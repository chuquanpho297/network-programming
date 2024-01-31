package com.networking.meetingclient.protocol.request;

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
    private String session;

    public Request(String action) {
        this.action = action;
    }


}