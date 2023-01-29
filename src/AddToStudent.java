import io.github.cdimascio.dotenv.Dotenv;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;

public class AddToStudent {
    Dotenv dotenv=Dotenv.load();
    void InsertIntoTable(StudentInfo studentInfo)
    {
        try(Connection conn= DriverManager.getConnection(dotenv.get("URL"), dotenv.get("USERNAME"), dotenv.get("PASS"));
            Statement stmt=conn.createStatement();)
        {
            String selectDatabase="USE NUCLEIASSIGNMENT";
            stmt.execute(selectDatabase);
            PreparedStatement insertQuery=conn.prepareStatement("INSERT INTO STUDENTINFO VALUES (?,?,?,?)");
            insertQuery.setString(1,studentInfo.fullName);
            insertQuery.setString(2,String.valueOf(studentInfo.rollNo));
            insertQuery.setString(3,String.valueOf(studentInfo.age));
            insertQuery.setString(4,studentInfo.address);
            insertQuery.executeUpdate();
        } catch(SQLIntegrityConstraintViolationException e){
            System.out.println("Duplicate Roll No.");
        }catch (SQLException ex){
            System.out.println("Database Connection Error");
        }
    }
}
