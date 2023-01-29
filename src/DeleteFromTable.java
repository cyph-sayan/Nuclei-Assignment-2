import io.github.cdimascio.dotenv.Dotenv;
import java.sql.*;
public class DeleteFromTable {
    Dotenv dotenv = Dotenv.load();
    void deleteData(int rollNo) {
        try (Connection conn = DriverManager.getConnection(dotenv.get("URL"), dotenv.get("USERNAME"), dotenv.get("PASS"));
             Statement stmt = conn.createStatement();) {
            String sql = "USE NUCLEIASSIGNMENT";
            stmt.execute(sql);
            PreparedStatement pstmt1 = conn.prepareStatement("DELETE FROM STUDENTINFO WHERE ROLL=(?)");
            pstmt1.setString(1, String.valueOf(rollNo));
            int a=pstmt1.executeUpdate();
            if(a==0)
            {
                System.out.println("No Student Details Found For The Entered Roll No.");;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
