package com.networking.meetingclient.models;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Integer id;
    private String username;
    private String fullname;
    private String role;
    private String session;
}
