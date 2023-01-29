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
