package com.networking.meetingclient.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RequestEnum {

    LOGIN("LOGIN"),
    REGISTER("REGISTER"),
    LOGOUT("LOGOUT");

    private final String request;


}