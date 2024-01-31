package com.networking.meetingclient.models;


import com.networking.meetingclient.util.Role;
import lombok.Builder;
import lombok.ToString;

@ToString(callSuper = true)
public class Student extends User {

    @Builder
    public Student(Integer id, String username, String fullname, String userSession) {
        super(id, username, fullname, Role.STUDENT, userSession);
    }
}
