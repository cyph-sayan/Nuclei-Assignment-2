import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.ArrayList;
import java.util.List;
import database.DatabaseHandler;
import database.DatabaseHandlerImpl;
import enums.Course;
import models.requests.CreateUserRequest;
import models.requests.DeleteUserRequest;
import models.requests.GetUserRequest;
import services.DatabaseServices;

public class Main {
    static Scanner sc=new Scanner(System.in);
    boolean isValidNoOfCourses(List<String> courses){
        return (courses.size()>=4 || courses.size()<=6);
    }
    List<Course> getCourses() {
        List<Course> courses=new ArrayList<>();
        while (true) {
            System.out.println("Enter Course to be registered For: A, B, C, D, E, F\nA minimum registration of 4 courses is mandatory");
            AtomicBoolean isValid = new AtomicBoolean(true);
            List<String> inputCourses = Arrays.asList(sc.nextLine().split(","));
            if (!isValidNoOfCourses(inputCourses)) {
                System.out.println("Please Enter a valid value in the range 4-6");
                continue;
            }
            inputCourses.forEach(inputCourse -> {
                if (!Course.names.contains(inputCourse.toUpperCase())) {
                    System.out.println("Invalid Course Entered");
                    isValid.set(false);
                }
            });
            if (isValid.get()) {
                inputCourses.forEach(inputCourse -> {
                    courses.add(Course.valueOf(inputCourse.toUpperCase()));
                });
            }
            break;
        }
        return courses;
    }
    CreateUserRequest createUserRequest(){
        System.out.println("Add Student Details");
        System.out.println("Enter Name:");
        String name = sc.nextLine();
        System.out.println("Enter Roll No.:");
        int roll = Integer.parseInt(sc.nextLine());
        System.out.println("Enter Age:");
        int age = Integer.parseInt(sc.nextLine());
        System.out.println("Enter Address");
        String address = sc.nextLine();
        List<Course> courses=getCourses();
        return new CreateUserRequest(name,age,address,roll,courses);
    }
    public static void main(String[] args)  {
        int option;
        DatabaseHandler db=new DatabaseHandlerImpl();
        try {
            DatabaseServices databaseServices =new DatabaseServices();
            while (true) {
                System.out.println("1.Add Student Details\n2.Display Student Details\n3.Delete Student Details\n4.Exit");
                option = Integer.parseInt(sc.nextLine());
                switch (option) {
                    case 1:
                        Main ob=new Main();
                        databaseServices.createStudent(ob.createUserRequest());
                        break;
                    case 2:
                        System.out.println("Enter the Roll No. of the Student to view the data or * for all the data");
                        String rollNo=sc.nextLine();
                        if(rollNo.equals("*"))
                        {
                            databaseServices.getStudents();
                        }
                        else
                            databaseServices.getStudent(new GetUserRequest(Integer.parseInt(rollNo)));
                        break;
                    case 3:
                        System.out.println("Enter Roll Number To Be Deleted");
                        databaseServices.deleteStudent(new DeleteUserRequest(Integer.parseInt(sc.nextLine())));
                        break;
                    case 5:
                        db.closeConnection();
                        System.exit(0);
                    default:
                        System.out.println("Invalid Choice");
                }
                System.out.println();
            }
        }finally {
            sc.close();
        }
    }
}

