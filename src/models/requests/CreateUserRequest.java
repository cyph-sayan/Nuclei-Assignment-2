package models.requests;

import enums.Course;

public class CreateUserRequest {
    private String rollNumber;
    private String name;
    private int age;
    private String address;
    private Course[] courses; 
}
