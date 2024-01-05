package com.networking.meetingclient.protocol;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;

@Data
class Order {
    private int id;
    private Customer customer;
}

@Data
class Customer {
    private int id;
    private Address address;
}

@Data
class Address {
    private String street;
    private String city;
}

@Data
class Student {
    private String name;
    private int age;

    // constructors, getters and setters are omitted for brevity
}

public class Test {

    public static void main(String[] args) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();

        String orderJson = "{\n" +
                "  \"id\": 456,\n" +
                "  \"customer\": {\n" +
                "    \"id\": 789,\n" +
                "    \"address\": {\n" +
                "      \"street\": \"123 Main Street\",\n" +
                "      \"city\": \"SF\"\n" +
                "    }\n" +
                "  }\n" +
                "}";

        Order order = objectMapper.readValue(orderJson, Order.class);

        System.out.println(order.getId());
        System.out.println(order.getCustomer().getId());
        System.out.println(order.getCustomer().getAddress().getStreet());
        System.out.println(order.getCustomer().getAddress().getCity());
    }
}
