package com.networking.meetingclient.protocol.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@ToString(callSuper = true)
@NoArgsConstructor
@Setter
public class ErrorResponse extends Response {
    private String message;
}