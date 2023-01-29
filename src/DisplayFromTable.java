import io.github.cdimascio.dotenv.Dotenv;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DisplayFromTable {
    void DisplayFromTable()
    {
        Dotenv dotenv=Dotenv.load();
        try(Connection conn= DriverManager.getConnection(dotenv.get("URL"), dotenv.get("USERNAME"), dotenv.get("PASS"));
            Statement stmt=conn.createStatement();)
        {
            String sql="USE nucleiassignment";
            stmt.execute(sql);
            String query="SELECT * from studentinfo order by name desc";
            ResultSet rs=stmt.executeQuery(query);
            System.out.printf("%20s %5s %5s %15s","NAME","AGE","ROLL", "COURSES");
            System.out.println();
            while(rs.next())
            {
                List<String> courses=new ArrayList<String>();
                String name=rs.getString("Name");
                int age=rs.getInt("Age");
                int roll=rs.getInt("Roll");
                String address=rs.getString("Address");
                PreparedStatement preparedStatement=conn.prepareStatement("Select courseid from courseinfo where roll=(?) ORDER BY COURSEID ASC");
                preparedStatement.setString(1,String.valueOf(roll));
                ResultSet rs1=preparedStatement.executeQuery();
                while(rs1.next())
                {
                    courses.add(rs1.getString(1));
                }
                System.out.printf("%20s %5s %5s %20s",name,age,roll,courses);
                System.out.println();
            }
        } catch(SQLException e){
            e.printStackTrace();
        }
    }
}
