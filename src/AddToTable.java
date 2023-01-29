import io.github.cdimascio.dotenv.Dotenv;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
public class AddToTable {
    Dotenv dotenv=Dotenv.load();
    void InsertIntoTable(StudentInfo studentInfo)
    {
        try(Connection conn= DriverManager.getConnection(dotenv.get("URL"), dotenv.get("USERNAME"), dotenv.get("PASS"));
            Statement stmt=conn.createStatement();)
        {
            String sql="USE NUCLEIASSIGNMENT";
            stmt.execute(sql);
            PreparedStatement pstmt1=conn.prepareStatement("INSERT INTO STUDENTINFO VALUES (?,?,?,?)");
            pstmt1.setString(1,studentInfo.fullName);
            pstmt1.setString(2,String.valueOf(studentInfo.rollNo));
            pstmt1.setString(3,String.valueOf(studentInfo.age));
            pstmt1.setString(4,studentInfo.address);
            pstmt1.executeUpdate();
        } catch(SQLIntegrityConstraintViolationException e){
            System.out.println("Duplicate Roll No.");
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        try(Connection conn= DriverManager.getConnection(dotenv.get("URL"), dotenv.get("USERNAME"), dotenv.get("PASS"));
            Statement stmt=conn.createStatement();){
            String sql="USE NUCLEIASSIGNMENT";
            stmt.execute(sql);
            PreparedStatement pstmt2=conn.prepareStatement("INSERT INTO COURSEINFO VALUES (?,?)");
            pstmt2.setString(2,String.valueOf(studentInfo.rollNo));
            for(int i=0; i<studentInfo.courses.size();i++){
                pstmt2.setString(1,String.valueOf(studentInfo.courses.get(i)));
                pstmt2.executeUpdate();
            }
        }catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}
