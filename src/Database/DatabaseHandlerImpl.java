package Database;
import enums.Course;
import io.github.cdimascio.dotenv.Dotenv;
import models.entities.User;
import models.requests.CreateUserRequest;
import models.requests.DeleteUserRequest;
import models.requests.ListUserRequest;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DatabaseHandlerImpl implements DatabaseHandler{
    Dotenv dotenv=Dotenv.load();
    Connection connection;
    public DatabaseHandlerImpl(){
        try {
            connection = DriverManager.getConnection(Objects.requireNonNull(dotenv.get("URL")), dotenv.get("USERNAME"), dotenv.get("PASS"));
        }catch (SQLException e){
            System.out.println("Database Not Available");
        }
    }
    @Override
    public void createUser(CreateUserRequest req){
        try
        {
            PreparedStatement insertQuery1=connection.prepareStatement("INSERT INTO STUDENTINFO VALUES (?,?,?,?)");
            insertQuery1.setString(1,req.getFullName());
            insertQuery1.setString(2,String.valueOf(req.getRollNo()));
            insertQuery1.setString(3,String.valueOf(req.getAge()));
            insertQuery1.setString(4, req.getAddress());
            insertQuery1.executeUpdate();
            PreparedStatement insertQuery2=connection.prepareStatement("INSERT INTO COURSEINFO VALUES (?,?)");
            insertQuery2.setString(2,String.valueOf(req.getRollNo()));
            for(int i=0; i<req.getCourses().size();i++){
                insertQuery2.setString(1,String.valueOf(req.getCourses().get(i)));
                insertQuery2.executeUpdate();
            }
        }catch (SQLIntegrityConstraintViolationException e){
            System.out.println("Roll No. Already Present");
        }catch (SQLException e){
            System.out.println("Database Not Available");
        }
    }

    @Override
    public void deleteUser(DeleteUserRequest req) {
        try{
            PreparedStatement insertQuery = connection.prepareStatement("DELETE FROM STUDENTINFO WHERE ROLL=(?)");
            insertQuery.setString(1, String.valueOf(req.getRollNo()));
            int rowsAffected=insertQuery.executeUpdate();
            if(rowsAffected==0)
            {
                System.out.println("No Student Details Found For The Entered Roll No.");
            }
            else {
                System.out.println("Student Data Deleted Successfully");
            }
        } catch (SQLException e) {
            System.out.println("Database Connection Error");
        }
    }
    @Override
    public User listUser(ListUserRequest req){
        User user=null;
        try
        {
            PreparedStatement selectQuery;
            selectQuery= connection.prepareStatement("SELECT * FROM STUDENTINFO WHERE roll=(?)");
            selectQuery.setString(1,String.valueOf(req.getRollNo()));
            try(ResultSet rs=selectQuery.executeQuery()) {
                List<Course> courses = new ArrayList<>();
                String name = rs.getString("Name");
                int age = rs.getInt("Age");
                int roll = rs.getInt("Roll");
                String address = rs.getString("Address");
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT COURSEID FROM COURSEINFO WHERE ROLL=(?) ORDER BY COURSEID ASC");
                preparedStatement.setString(1, String.valueOf(roll));
                ResultSet rs1 = preparedStatement.executeQuery();
                while (rs1.next()) {
                    courses.add(Course.valueOf(rs1.getString(1).toUpperCase()));
                }
                user = new User(name, age, address, roll, courses);
            }catch (SQLException e){
                System.out.println("Data doesnt Exists for the given roll");
            }
        }catch(SQLException e){
            e.printStackTrace();
            System.out.println("Database Connection Error");
        }
        return user;
    }
    @Override
    public void closeConnection() {
        try {
            connection.close();
        }catch (SQLException e){
            System.out.println("Connection Already Closed");
        }
    }
}
