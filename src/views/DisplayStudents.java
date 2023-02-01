package views;
import models.entities.User;

public class DisplayStudents {
    public void display(User user) {
        System.out.printf("%20s %5s %5s %10s %18s","NAME","AGE","ROLL","ADDRESS","COURSES");
        System.out.println();
        System.out.printf("%20s %5s %5s %10s %18s",user.getFullName(),user.getAge(),user.getRollNo(),user.getAddress(),user.getCourses());
        }
    }

