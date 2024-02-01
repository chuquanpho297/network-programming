package com.networking.meetingclient.seed;

import com.networking.meetingclient.models.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class StudentSeed {

    private static Student getRandomStudent() {
        return Student.builder()
                .id(new Random().nextInt(1000)).username("username")
                .fullname(new Random().nextInt(1000) + "fullname")
                .build();
    }

    public static List<Student> getStudents() {
        List<Student> students = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            students.add(getRandomStudent());
        }
        return students;
    }
}
