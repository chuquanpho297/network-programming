package com.networking.meetingclient.service;

import com.networking.meetingclient.StateManager;
import com.networking.meetingclient.protocol.request.LoginRequest;
import com.networking.meetingclient.protocol.request.LogoutRequest;
import com.networking.meetingclient.protocol.request.RegisterRequest;
import com.networking.meetingclient.util.JacksonUtil;

import java.util.Objects;

public class UserService {

    private static UserService userService;

    public static UserService getInstance() {
        if (Objects.isNull(userService)) {
            userService = new UserService();
        }
        return userService;
    }

    public void login(String username, String password) {
        LoginRequest loginRequest = LoginRequest.builder()
                .username(username)
                .password(password)
                .build();

        try {
            String json = JacksonUtil.getInstance().writeValueAsString(loginRequest);
            String receiveMess = StateManager.getInstance().getClientSocket().sendAndReceive(json);
            System.out.println("Receive: " + receiveMess);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void logout() {
        LogoutRequest logoutRequest = LogoutRequest.builder().build();

        try {
            String json = JacksonUtil.getInstance().writeValueAsString(logoutRequest);
            String receiveMess = StateManager.getInstance().getClientSocket().sendAndReceive(json);
            System.out.println("Receive: " + receiveMess);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void register(String username, String password, String fullname, String role) {
        RegisterRequest registerRequest = RegisterRequest.builder()
                .username(username)
                .password(password)
                .fullname(fullname)
                .role(role)
                .build();
        System.out.println(registerRequest.toString());
        try {
            String json = JacksonUtil.getInstance().writeValueAsString(registerRequest);
            String receiveMess = StateManager.getInstance().getClientSocket().sendAndReceive(json);
            System.out.println("Receive: " + receiveMess);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
