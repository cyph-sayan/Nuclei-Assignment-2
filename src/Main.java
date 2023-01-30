import java.util.*;
import Database.DatabaseHandler;
import Database.DatabaseHandlerImpl;
import enums.Course;
import enums.SortFieldOptions;
import enums.SortOption;
import models.requests.CreateUserRequest;
import models.requests.DeleteUserRequest;
import models.requests.ListUserRequest;
import views.DisplayStudents;

public class Main {
    public static Set<String> courseSet=new HashSet<>();
    boolean isValidCourse(String course){
        return courseSet.contains(course);
    }
    public static void main(String[] args)  {
        Collections.addAll(courseSet,"A","B","C","D","E","F");
        Scanner sc = new Scanner(System.in);
        Main ab=new Main();
        int option,age,noOfCourses,roll;
        String name,address,course;
        DatabaseHandler db=new DatabaseHandlerImpl();
        try {
            while (true) {
                System.out.println("1.Add Student Details\n2.Display Student Details\n3.Delete Student Details\n4.Save To File\n5.Exit");
                option = Integer.parseInt(sc.nextLine());
                switch (option) {
                    case 1:
                        System.out.println("Add Student Details");
                        System.out.println("Enter Name:");
                        name = sc.nextLine();
                        System.out.println("Enter Roll No.:");
                        roll = Integer.parseInt(sc.nextLine());
                        System.out.println("Enter Age:");
                        age = Integer.parseInt(sc.nextLine());
                        System.out.println("Enter Address");
                        address = sc.nextLine();
                        System.out.println("Enter Course to be registered For: A, B, C, D, E, F\nA minimum registration of 4 courses is mandatory\nEnter the no. of courses to be registered");
                        do {
                            noOfCourses = Integer.parseInt(sc.nextLine());
                            if(noOfCourses<4){
                                System.out.println("Please Enter a Number Greater Than 4");
                            }
                        }while (noOfCourses<4);
                        System.out.println("Enter Courses to be Registered:");
                        List<Course> courses=new ArrayList<>();
                        while (noOfCourses != 0) {
                            course = sc.nextLine();
                            if (ab.isValidCourse(course.toUpperCase())){
                                noOfCourses = noOfCourses - 1;
                                courses.add(Course.valueOf(course.toUpperCase()));
                            } else{
                                System.out.println("Invalid Course");
                            }
                        }
                        CreateUserRequest createUserRequest=new CreateUserRequest(name,age,address,roll,courses);
                        db.createUser(createUserRequest);
                        break;
                    case 2:
                        ListUserRequest listUserRequest=new ListUserRequest(SortOption.ASC, SortFieldOptions.ADDRESS);
                        DisplayStudents ds=new DisplayStudents();
                        ds.display(db.listUser(listUserRequest));
                        break;
                    case 3:
                        System.out.println("Enter Roll Number To Be Deleted");
                        DeleteUserRequest deleteUserRequest=new DeleteUserRequest(Integer.parseInt(sc.nextLine()));
                        db.deleteUser(deleteUserRequest);
                        break;
                    case 5:
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

