package database;
import models.entities.User;
import models.requests.CreateUserRequest;
import models.requests.DeleteUserRequest;
import models.requests.GetUserRequest;

import java.sql.SQLException;
import java.util.List;

public interface DatabaseHandler {
    User createUser(String studentQueryStatement,String courseStudentQueryStatement, CreateUserRequest req)throws SQLException;
    void deleteUser(String deleteUserStatement, DeleteUserRequest req)throws SQLException;
    List<User> getUsers(String listStudentsStatement,String getCoursesStatement, int pageSize, int pageToken)throws SQLException;
    User getUser(String getStudentStatement, String getCourseStatement,GetUserRequest req)throws SQLException;
    void closeConnection();
}
