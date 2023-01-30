package models.entities;
import enums.Course;

import java.util.List;

public class User {
    public String fullName;
    public int age;
    public String address;
    public int rollNo;
    public List<Course> courses;
    public User(String name, int age, String address, int rollNo, List<Course> courses){
        this.fullName=name;
        this.address=address;
        this.age=age;
        this.rollNo=rollNo;
        this.courses=courses;
    }
}
