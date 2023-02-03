package services;

import constants.QueryStatementPathConstants;
import database.DatabaseHandler;
import database.DatabaseHandlerImpl;
import filehandlers.FileHandler;
import filehandlers.FileHandlerImpl;
import models.entities.User;
import models.requests.CreateUserRequest;
import models.requests.DeleteUserRequest;
import models.requests.GetUserRequest;
import models.requests.ListUserRequest;
import java.sql.SQLException;
import java.util.List;

public class DatabaseServices {
    FileHandler fileHandler;
    DatabaseHandler databaseHandler;
    public DatabaseServices()
    {
        fileHandler=new FileHandlerImpl();
        databaseHandler=new DatabaseHandlerImpl();
    }
    public void createStudent(CreateUserRequest user){
        databaseHandler=new DatabaseHandlerImpl();
        try{
            databaseHandler.createUser(fileHandler.getQueryStatement(QueryStatementPathConstants.INSERT_STUDENT), fileHandler.getQueryStatement(QueryStatementPathConstants.INSERT_COURSE),user);
        }catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
    }
    public void deleteStudent(DeleteUserRequest user){
        try{
            databaseHandler.deleteUser(fileHandler.getQueryStatement(QueryStatementPathConstants.DELETE_STUDENT),user);
        }catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
    }
    public int listGetStudents(ListUserRequest listUserRequest,int pageToken)
    {
        try{
            List<User> students=databaseHandler.listGetUsers(fileHandler.getQueryStatement(QueryStatementPathConstants.LIST_STUDENT), fileHandler.getQueryStatement(QueryStatementPathConstants.SELECT_COURSES),listUserRequest.pageSize,pageToken,listUserRequest.sortField,listUserRequest.sortWays);
            students.forEach(student-> System.out.println(student.toString()));
            return listUserRequest.pageSize;
        }catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
        return 0;
    }
    public void getStudent(GetUserRequest request){
        try{
            User student=databaseHandler.getUser(fileHandler.getQueryStatement(QueryStatementPathConstants.SELECT_STUDENT), fileHandler.getQueryStatement(QueryStatementPathConstants.SELECT_COURSES), request);
            System.out.println(student.toString());
        }catch (SQLException ex){
            ex.printStackTrace();
            System.out.println(ex.getMessage());
        }
    }
    public void closeConnection(){
        databaseHandler.closeConnection();
    }
}
