import io.github.cdimascio.dotenv.Dotenv;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
public class DeleteFromTable {
    Dotenv dotenv = Dotenv.load();
    void deleteData(int rollNo) {
        try (Connection conn = DriverManager.getConnection(dotenv.get("URL"), dotenv.get("USERNAME"), dotenv.get("PASS"));
             Statement stmt = conn.createStatement();) {
            String selectDatabase = "USE NUCLEIASSIGNMENT";
            stmt.execute(selectDatabase);
            PreparedStatement insertQuery = conn.prepareStatement("DELETE FROM STUDENTINFO WHERE ROLL=(?)");
            insertQuery.setString(1, String.valueOf(rollNo));
            int rowsAffected=insertQuery.executeUpdate();
            if(rowsAffected==0)
            {
                System.out.println("No Student Details Found For The Entered Roll No.");;
            }
            else {
                System.out.println("Student Data Deleted Successfully");
            }
        } catch (SQLException e) {
            System.out.println("Database Connection Error");
        }
    }
}
