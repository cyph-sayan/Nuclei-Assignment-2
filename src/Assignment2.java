import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import java.util.Scanner;
class StudentInfo implements Serializable
{
    protected String fullName;
    protected int age;
    protected String address;
    protected int rollNo;
    protected List<String> courses;
    StudentInfo(String name, int age, String address, int rollNo, List<String> courses){
        this.fullName=name;
        this.address=address;
        this.age=age;
        this.rollNo=rollNo;
        this.courses=courses;
    }
}
public class Assignment2 {
    boolean isValidCourse(String course){
        Set<String> courseSet=new HashSet<>();
        courseSet.add("A");
        courseSet.add("B");
        courseSet.add("C");
        courseSet.add("D");
        courseSet.add("E");
        courseSet.add("F");
        return courseSet.contains(course);
    }
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        Assignment2 ab=new Assignment2();
        int option,age,noOfCourses,roll;
        String name,address,course;

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
                        List<String> courses = new ArrayList<>();
                        System.out.println("Enter Courses to be Registered:");
                        while (noOfCourses != 0) {
                            course = sc.nextLine();
                            if (ab.isValidCourse(course.toUpperCase())){
                                courses.add(course);
                                noOfCourses = noOfCourses - 1;
                            } else{
                                System.out.println("Invalid Course");
                            }
                        }
                        StudentInfo studentInfo = new StudentInfo(name, age, address, roll, courses);
                        AddToStudent addToStudent = new AddToStudent();
                        AddToCourseInfo addToCourseInfo = new AddToCourseInfo();
                        addToStudent.InsertIntoTable(studentInfo);
                        addToCourseInfo.addToCourse(studentInfo);
                        break;
                    case 2:
                        DisplayFromTable dp = new DisplayFromTable();
                        dp.display();
                        break;
                    case 3:
                        DeleteFromTable dl = new DeleteFromTable();
                        System.out.println("Enter Roll Number To Be Deleted");
                        dl.deleteData(Integer.parseInt(sc.nextLine()));
                        break;
                    case 4:
                        SaveToFile sv = new SaveToFile();
                        sv.save();
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

