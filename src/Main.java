import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.ArrayList;
import java.util.List;
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
    public static void main(String[] args)  {
        Scanner sc = new Scanner(System.in);
        int option,age,roll;
        String name,address;
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
                        List<Course> courses=new ArrayList<>();
                        while (true)
                        {
                            courses.removeAll(courses);
                            System.out.println("Enter Course to be registered For: A, B, C, D, E, F\nA minimum registration of 4 courses is mandatory");
                            boolean isValid=true;
                            String [] courseArray=sc.nextLine().split(",");
                            if(courseArray.length<4 || courseArray.length>6) {
                                System.out.println("Please Enter a valid value in the range 4-6");
                                continue;
                            }
                            else {
                                for(String course:courseArray){
                                    if(!Stream.of(Course.values()).map(Course::name).collect(Collectors.toList()).contains(course))
                                    {
                                        System.out.println("Invalid Course Entered");
                                        isValid=false;
                                        break;
                                    }
                                }
                            }
                            if (isValid){
                                for (String course:courseArray) {
                                    courses.add(Course.valueOf(course.toUpperCase()));
                                }
                                break;
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

