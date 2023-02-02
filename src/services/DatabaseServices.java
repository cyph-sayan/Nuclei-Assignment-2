package services;

import constants.DatabaseFieldConstants;
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
    public static int pageToken=0;
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
    public void getStudents(ListUserRequest listUserRequest)
    {
        try{
            List<User> students=databaseHandler.getUsers(fileHandler.getQueryStatement(QueryStatementPathConstants.LIST_STUDENT)+DatabaseFieldConstants.SORT+DatabaseFieldConstants.SORT_FIELD[listUserRequest.sortField-1]+DatabaseFieldConstants.SORT_WAYS[listUserRequest.sortWays-1]+DatabaseFieldConstants.PAGE_SIZE +listUserRequest.pageSize+DatabaseFieldConstants.TOKEN+pageToken, fileHandler.getQueryStatement(QueryStatementPathConstants.SELECT_COURSES),listUserRequest.pageSize,pageToken);
            students.forEach(student-> System.out.println(student.toString()));
            pageToken=listUserRequest.pageSize;
        }catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
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
