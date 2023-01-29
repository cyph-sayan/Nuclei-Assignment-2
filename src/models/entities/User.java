package models.entities;
import java.util.List;

import enums.Course;

public class User {
    private int rowId;
    private String rollNumber;
    private String name;
    private int age;
    private String address;
    private Course[] courses;

    public User(String rollNumber, String name, int age, String address, Course[] courses) {
        this.rollNumber = rollNumber;
        this.name = name;
        this.age = age;
        this.address = address;
        this.courses = courses;
    }
}
