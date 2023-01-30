package models.requests;
import enums.Course;
import java.util.List;

public class CreateUserRequest {
    private String fullName;
    private int age;
    private String address;
    private int rollNo;
    private List<Course> courses;
    public CreateUserRequest(String name, int age, String address, int rollNo, List<Course> courses){
        this.fullName=name;
        this.address=address;
        this.age=age;
        this.rollNo=rollNo;
        this.courses=courses;
    }
    public String getFullName() {
        return fullName;
    }
    public int getAge() {
        return age;
    }
    public int getRollNo() {
        return rollNo;
    }
    public String getAddress() {
        return address;
    }
    public List<Course> getCourses() {
        return courses;
    }
}
