import io.github.cdimascio.dotenv.Dotenv;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DisplayFromTable {
    void display()
    {
        Scanner sc=new Scanner(System.in);
        Dotenv dotenv=Dotenv.load();
        System.out.println("Enter the Field to be used for Ordering: Name, Roll, Age, Address");
        String field=sc.nextLine();
        System.out.println("Enter 1.Ascending 2.Descending");
        int typeOfOrdering=Integer.parseInt(sc.nextLine());
        try(Connection conn= DriverManager.getConnection(dotenv.get("URL"), dotenv.get("USERNAME"), dotenv.get("PASS"));
            Statement stmt=conn.createStatement())
        {
            String selectDatabase="USE nucleiassignment";
            stmt.execute(selectDatabase);
            PreparedStatement selectQuery;
            if(typeOfOrdering==1)
                selectQuery= conn.prepareStatement("SELECT * FROM STUDENTINFO ORDER BY (?) ASC");
            else
                selectQuery= conn.prepareStatement("SELECT * FROM STUDENTINFO ORDER BY (?) DESC");
            selectQuery.setString(1,field);
            ResultSet rs=selectQuery.executeQuery();
            System.out.printf("%20s %5s %5s %10s %15s","NAME","AGE","ROLL","ADDRESS","COURSES");
            System.out.println();
            while(rs.next())
            {
                List<String> courses=new ArrayList<>();
                String name=rs.getString("Name");
                int age=rs.getInt("Age");
                int roll=rs.getInt("Roll");
                String address=rs.getString("Address");
                PreparedStatement preparedStatement=conn.prepareStatement("SELECT COURSEID FROM COURSEINFO WHERE ROLL=(?) ORDER BY COURSEID ASC");
                preparedStatement.setString(1,String.valueOf(roll));
                ResultSet rs1=preparedStatement.executeQuery();
                while(rs1.next())
                {
                    courses.add(rs1.getString(1));
                }
                System.out.printf("%20s %5s %5s %10s %20s",name,age,roll,address,courses);
                System.out.println();
            }
        } catch(SQLException e){
            e.printStackTrace();
            System.out.println("Database Connection Error");
        }
    }
}
