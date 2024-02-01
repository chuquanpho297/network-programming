package com.networking.meetingclient.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.networking.meetingclient.util.JacksonUtil;
import com.networking.meetingclient.util.JavaFxUtil;
import com.networking.meetingclient.util.ResponseEnum;

public class Service {

    private final ObjectMapper objectMapper = JacksonUtil.getInstance();

    public String handleResponse(String receiveMess) {
        try {
            JsonNode jsonNode = objectMapper.readTree(receiveMess);
            String value = jsonNode.get("code").asText(); // "value"
            if (value.endsWith("FAIL") || value.equals(ResponseEnum.UNAUTHORIZED.getResponse())) {
                JavaFxUtil.createAlert("Error", "Error", jsonNode.get("message").asText());
                return null;
            } else if (value.endsWith("OK")) {
                return value;
            }
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        } finally {
            return null;
        }
    }
}

