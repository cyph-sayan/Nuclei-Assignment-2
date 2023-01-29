import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
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
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("1.Add Student Details\n2.Display Student Details\n3.Delete Student Details\n4.Save To File\n5.Exit");
            int option = Integer.parseInt(sc.nextLine());
            switch (option) {
                case 1:
                    System.out.println("Add Student Details");
                    System.out.println("Enter Name:");
                    String name = sc.nextLine();
                    System.out.println("Enter Roll No.:");
                    int roll = Integer.parseInt(sc.nextLine());
                    System.out.println("Enter Age:");
                    int age = Integer.parseInt(sc.nextLine());
                    System.out.println("Enter Address");
                    String address = sc.nextLine();
                    System.out.println("Enter Course to be registered For: A, B, C, D, E, F");
                    System.out.println("A minimum registration of 4 courses is mandatory");
                    System.out.println("Enter the no. of courses to be registered");
                    int n = Integer.parseInt(sc.nextLine());
                    List<String> courses = new ArrayList<>();
                    for (int i = 0; i < n; i++) {
                        courses.add(sc.nextLine());
                    }
                    StudentInfo so = new StudentInfo(name, age, address, roll, courses);
                    AddToTable ad = new AddToTable();
                    try {
                        ad.InsertIntoTable(so);
                    } catch (Exception e) {
                        System.out.println("Error");
                    }
                    break;
                case 2:
                    DisplayFromTable dp = new DisplayFromTable();
                    dp.DisplayFromTable();
                    break;
                case 3:
                    DeleteFromTable dl = new DeleteFromTable();
                    System.out.println("Enter Roll Number To Be Deleted");
                    dl.deleteData(Integer.parseInt(sc.nextLine()));
                    break;
                case 4:
                    SaveToFile sv=new SaveToFile();
                    sv.save();
                    break;
                case 5:
                    System.exit(0);
                default:
                    System.out.println("Invalid Choice");
            }
        }
    }
}

