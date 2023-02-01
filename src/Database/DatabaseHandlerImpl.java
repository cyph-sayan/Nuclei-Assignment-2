package Database;
import constants.databasefieldconstants;
import enums.Course;
import errormessages.ExceptionErrorMessages;
import io.github.cdimascio.dotenv.Dotenv;
import models.entities.User;
import models.requests.CreateUserRequest;
import models.requests.DeleteUserRequest;
import models.requests.ListUserRequest;
import sql.studentquery;
import successmessages.SuccessMessages;

import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DatabaseHandlerImpl implements DatabaseHandler{
    Dotenv dotenv=Dotenv.load();
    Connection connection;
    Statement stmt;
    public DatabaseHandlerImpl(){
        try {
            connection = DriverManager.getConnection(Objects.requireNonNull(dotenv.get("URL")), dotenv.get("USERNAME"), dotenv.get("PASS"));
            stmt=connection.createStatement();
        }catch (SQLException e){
            System.out.println(ExceptionErrorMessages.SQL_DATABASE_CONNECTION_ERROR);
        }
    }
    @Override
    public void createUser(CreateUserRequest req){
        try
        {
            String student=sql.studentquery.insertIntoStudent(req.getFullName(),String.valueOf(req.getRollNo()),String.valueOf(req.getAge()),req.getAddress());
            System.out.println(student);
            stmt.executeUpdate(student);
            for(int i=0; i<req.getCourses().size();i++){
                String course=studentquery.insertIntoCourse(String.valueOf(req.getCourses().get(i)),String.valueOf(req.getRollNo()));
                stmt.executeUpdate(course);
            }
        }catch (SQLIntegrityConstraintViolationException e){
            System.out.println(ExceptionErrorMessages.SQL_DUPLICATE_ROLL_NO_ERROR);
        }catch (SQLException e){
            System.out.println(ExceptionErrorMessages.SQL_DATABASE_CONNECTION_ERROR);
        }
    }

    @Override
    public void deleteUser(DeleteUserRequest req) {
        try{
            String delUser=studentquery.delStudentDetails(String.valueOf(req.getRollNo()));
            int rowsAffected=stmt.executeUpdate(delUser);
            if(rowsAffected==0)
            {
                System.out.println(ExceptionErrorMessages.SQL_NO_ROWS_AFFECTED);
            }
            else {
                System.out.println(SuccessMessages.SQL_DELETE_SUCCESS_MESSAGE);
            }
        } catch (SQLException e) {
            System.out.println(ExceptionErrorMessages.SQL_DATABASE_CONNECTION_ERROR);
        }
    }
    @Override
    public User getUser(ListUserRequest req){
        User user=null;
        try(ResultSet rs=stmt.executeQuery(studentquery.getStudentDetails(String.valueOf(req.getRollNo())))) {
            rs.next();
            List<Course> courses = new ArrayList<>();
            String name = rs.getString(databasefieldconstants.name);
            int age = rs.getInt(databasefieldconstants.age);
            int roll = rs.getInt(databasefieldconstants.roll);
            String address = rs.getString(databasefieldconstants.address);
            ResultSet rs1 = stmt.executeQuery(studentquery.getCourseDetails(String.valueOf(req.getRollNo())));
            while (rs1.next()) {
                courses.add(Course.valueOf(rs1.getString(1).toUpperCase()));
            }
            user=new User(name, age, address, roll, courses);
            }catch (SQLException e){
                System.out.println(ExceptionErrorMessages.SQL_NO_DATA_EXISTS_ERROR+e.getMessage());
            }
            return user;
    }

    @Override
    public List<User> getUsers() {
        List<User> users=new ArrayList<>();
        try{
            ResultSet rs=stmt.executeQuery(sql.studentquery.listStudents());
            while(rs.next()){
                List<Course> courses = new ArrayList<>();
                String name = rs.getString(databasefieldconstants.name);
                int age = rs.getInt(databasefieldconstants.age);
                int roll = rs.getInt(databasefieldconstants.roll);
                String address = rs.getString(databasefieldconstants.address);
                Statement stmt2=connection.createStatement();
                ResultSet rs1 = stmt2.executeQuery(studentquery.getCourseDetails(String.valueOf(roll)));
                while (rs1.next()) {
                    courses.add(Course.valueOf(rs1.getString(1).toUpperCase()));
                }
                users.add(new User(name, age, address, roll, courses));
            }
        }catch (SQLException e){
            System.out.println(ExceptionErrorMessages.SQL_NO_DATA_EXISTS_ERROR+e.getMessage());
        }
        return users;
    }
    @Override
    public void closeConnection() {
        try {
            connection.close();
        }catch (SQLException e){
            System.out.println(ExceptionErrorMessages.SQL_CLOSE_CONNECTION_ERROR+e.getMessage());
        }
    }
}
