package database;
import constants.DatabaseFieldConstants;
import enums.Course;
import errormessages.ExceptionErrorMessages;
import io.github.cdimascio.dotenv.Dotenv;
import models.entities.User;
import models.requests.CreateUserRequest;
import models.requests.DeleteUserRequest;
import models.requests.GetUserRequest;
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
    public User createUser(String insertStudentStatement, String insertCourseStatement, CreateUserRequest req)throws SQLException {
        PreparedStatement pstmt = connection.prepareStatement(insertStudentStatement);
        pstmt.setString(1, req.fullName);
        pstmt.setString(2, String.valueOf(req.rollNo));
        pstmt.setString(3, String.valueOf(req.age));
        pstmt.setString(4, req.address);
        pstmt.executeUpdate();
        pstmt = connection.prepareStatement(insertCourseStatement);
        pstmt.setString(2, String.valueOf(req.rollNo));
        for (int i = 0; i < req.courses.size(); i++) {
            pstmt.setString(1, String.valueOf(req.courses.get(i)));
            pstmt.executeUpdate();
        }
        return new User(req.fullName, req.age, req.address, req.rollNo,req.courses);
    }
    @Override
    public void deleteUser(String deleteUserStatement,DeleteUserRequest req)throws SQLException {
        PreparedStatement delUser = connection.prepareStatement(deleteUserStatement);
        delUser.setString(1, String.valueOf(req.rollNo));
        delUser.executeUpdate();
    }
    @Override
    public User getUser(String getStudentStatement, String getCourseStatement,GetUserRequest request)throws SQLException{
        PreparedStatement preparedStatement=connection.prepareStatement(getStudentStatement);
        preparedStatement.setString(1,String.valueOf(request.rollNo));
        ResultSet resultSet=preparedStatement.executeQuery();
        resultSet.next();
        List<Course> courses = new ArrayList<>();
        String name = resultSet.getString(DatabaseFieldConstants.NAME);
        int age = resultSet.getInt(DatabaseFieldConstants.AGE);
        int roll = resultSet.getInt(DatabaseFieldConstants.ROLL);
        String address = resultSet.getString(DatabaseFieldConstants.ADDRESS);
        preparedStatement=connection.prepareStatement(getCourseStatement);
        preparedStatement.setString(1,String.valueOf(request.rollNo));
        resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            courses.add(Course.valueOf(resultSet.getString(1).toUpperCase()));
        }
        return new User(name, age, address, roll, courses);
    }

    @Override
    public List<User> listGetUsers(String listStudentsStatement,String getCoursesStatement,int pageSize,int pageToken,int sortField, int sortWays)throws SQLException {
        List<User> users=new ArrayList<>();
        PreparedStatement preparedStatement=connection.prepareStatement(listStudentsStatement);
        preparedStatement.setString(1,DatabaseFieldConstants.SORT_FIELD[sortField-1]);
        preparedStatement.setString(2,DatabaseFieldConstants.SORT_WAYS[sortWays-1]);
        preparedStatement.setString(3,String.valueOf(pageToken));
        preparedStatement.setString(4,String.valueOf(pageToken));
        ResultSet resultSet= preparedStatement.executeQuery();
        while(resultSet.next()){
            List<Course> courses = new ArrayList<>();
            String name = resultSet.getString(DatabaseFieldConstants.NAME);
            int age = resultSet.getInt(DatabaseFieldConstants.AGE);
            int roll = resultSet.getInt(DatabaseFieldConstants.ROLL);
            String address = resultSet.getString(DatabaseFieldConstants.ADDRESS);
            preparedStatement=connection.prepareStatement(getCoursesStatement);
            preparedStatement.setString(1,String.valueOf(roll));
            ResultSet resultSet1 = preparedStatement.executeQuery();
            while (resultSet1.next()) {
                courses.add(Course.valueOf(resultSet1.getString(1).toUpperCase()));
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
