package com.networking.meetingclient.util;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResponseEnum {

    UNAUTHORIZED("UNAUTHORIZED"),

    REGISTER_OK("REGISTER_OK"),
    REGISTER_FAIL("REGISTER_FAIL"),

    LOGIN_OK("LOGIN_OK"),
    LOGIN_FAIL("LOGIN_FAIL"),

    LOGOUT_OK("LOGOUT_OK"),
    ;

    private final String response;
}
