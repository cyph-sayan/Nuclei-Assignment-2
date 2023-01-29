import io.github.cdimascio.dotenv.Dotenv;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
public class AddToCourseInfo {
    void addToCourse(StudentInfo studentInfo)
    {
        Dotenv dotenv=Dotenv.load();
        try(Connection conn= DriverManager.getConnection(dotenv.get("URL"), dotenv.get("USERNAME"), dotenv.get("PASS"));
            Statement stmt=conn.createStatement()){
            String selectDatabase="USE NUCLEIASSIGNMENT";
            stmt.execute(selectDatabase);
            PreparedStatement insertQuery=conn.prepareStatement("INSERT INTO COURSEINFO VALUES (?,?)");
            insertQuery.setString(2,String.valueOf(studentInfo.rollNo));
            for(int i=0; i<studentInfo.courses.size();i++){
                insertQuery.setString(1,studentInfo.courses.get(i));
                insertQuery.executeUpdate();
            }
            System.out.println("Data Inserted Successfully");
        }catch (SQLException e)
        {
            System.out.println("Database Connection Error");
        }
    }
}
