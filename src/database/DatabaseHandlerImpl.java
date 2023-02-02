package database;
import constants.DatabaseFieldConstants;
import enums.Course;
import errormessages.ExceptionErrorMessages;
import io.github.cdimascio.dotenv.Dotenv;
import models.entities.User;
import models.requests.CreateUserRequest;
import models.requests.DeleteUserRequest;
import models.requests.GetUserRequest;
import successmessages.SuccessMessages;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DatabaseHandlerImpl implements DatabaseHandler {
    Dotenv dotenv = Dotenv.load();
    Connection connection;
    Statement stmt;

    public DatabaseHandlerImpl() {
        try {
            connection = DriverManager.getConnection(Objects.requireNonNull(dotenv.get("URL")), dotenv.get("USERNAME"), dotenv.get("PASS"));
            stmt = connection.createStatement();
        } catch (SQLException e) {
            System.out.println(ExceptionErrorMessages.SQL_DATABASE_CONNECTION_ERROR);
        }
    }

    @Override
    public void createUser(String insertStudentStatement, String insertCourseStatement, CreateUserRequest req)throws SQLException {
        PreparedStatement pstmt = connection.prepareStatement(insertStudentStatement);
        pstmt.setString(1, req.getFullName());
        pstmt.setString(2, String.valueOf(req.getRollNo()));
        pstmt.setString(3, String.valueOf(req.getAge()));
        pstmt.setString(4, req.getAddress());
        pstmt.executeUpdate();
        PreparedStatement pstm2 = connection.prepareStatement(insertCourseStatement);
        pstm2.setString(2, String.valueOf(req.getRollNo()));
        for (int i = 0; i < req.getCourses().size(); i++) {
            pstm2.setString(1, String.valueOf(req.getCourses().get(i)));
            pstm2.executeUpdate();
        }
    }
    @Override
    public void deleteUser(String deleteUserStatement,DeleteUserRequest req)throws SQLException {
        PreparedStatement delUser = connection.prepareStatement(deleteUserStatement);
        delUser.setString(1, String.valueOf(req.getRollNo()));
        int rowsAffected = delUser.executeUpdate();
        if (rowsAffected == 0) {
            System.out.println(ExceptionErrorMessages.SQL_NO_ROWS_AFFECTED);
        } else {
            System.out.println(SuccessMessages.SQL_DELETE_SUCCESS_MESSAGE);
        }
    }
    @Override
    public User getUser(String getStudentStatement, String getCourseStatement,GetUserRequest request)throws SQLException{
        PreparedStatement ps=connection.prepareStatement(getStudentStatement);
        ps.setString(1,String.valueOf(request.getRollNo()));
        ResultSet rs=ps.executeQuery();
        rs.next();
        List<Course> courses = new ArrayList<>();
        String name = rs.getString(DatabaseFieldConstants.name);
        int age = rs.getInt(DatabaseFieldConstants.age);
        int roll = rs.getInt(DatabaseFieldConstants.roll);
        String address = rs.getString(DatabaseFieldConstants.address);
        PreparedStatement ps1=connection.prepareStatement(getCourseStatement);
        ps1.setString(1,String.valueOf(request.getRollNo()));
        ResultSet rs1 = ps1.executeQuery();
        while (rs1.next()) {
            courses.add(Course.valueOf(rs1.getString(1).toUpperCase()));
        }
        return new User(name, age, address, roll, courses);
    }

    @Override
    public List<User> getUsers(String listStudentsStatement,String getCoursesStatement)throws SQLException {
        List<User> users=new ArrayList<>();
            PreparedStatement ps=connection.prepareStatement(listStudentsStatement);
            ResultSet rs= ps.executeQuery();
            while(rs.next()){
                List<Course> courses = new ArrayList<>();
                String name = rs.getString(DatabaseFieldConstants.name);
                int age = rs.getInt(DatabaseFieldConstants.age);
                int roll = rs.getInt(DatabaseFieldConstants.roll);
                String address = rs.getString(DatabaseFieldConstants.address);
                PreparedStatement ps1=connection.prepareStatement(getCoursesStatement);
                ps1.setString(1,String.valueOf(roll));
                ResultSet rs1 = ps1.executeQuery();
                while (rs1.next()) {
                    courses.add(Course.valueOf(rs1.getString(1).toUpperCase()));
                }
                users.add(new User(name, age, address, roll, courses));
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
