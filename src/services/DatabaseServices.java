package services;

import database.DatabaseHandler;
import database.DatabaseHandlerImpl;
import filehandlers.FileHandler;
import filehandlers.FileHandlerImpl;
import models.entities.User;
import models.requests.CreateUserRequest;
import models.requests.DeleteUserRequest;
import models.requests.GetUserRequest;

import java.sql.SQLException;
import java.util.List;

public class DatabaseServices {
    public void createStudent(CreateUserRequest user){
        FileHandler insertStudentStatement=new FileHandlerImpl("InsertToStudent.sql");
        FileHandler insertCourseStatement=new FileHandlerImpl("InsertIntoCourse.sql");
        DatabaseHandler databaseHandler=new DatabaseHandlerImpl();
        try{
            databaseHandler.createUser(insertStudentStatement.getQueryStatement(),insertCourseStatement.getQueryStatement(),user);
        }catch (SQLException ex){
            System.out.println(ex.getMessage());;
        }
    }
    public void deleteStudent(DeleteUserRequest user){
        FileHandler deleteStudentStatement=new FileHandlerImpl("DeleteFromStudent.sql");
        DatabaseHandler databaseHandler=new DatabaseHandlerImpl();
        try{
            databaseHandler.deleteUser(deleteStudentStatement.getQueryStatement(),user);
        }catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
    }
    public void getStudents()
    {
        FileHandler getStudents=new FileHandlerImpl("ListStudents.sql");
        FileHandler getCourse=new FileHandlerImpl("SelectFromCourse.sql");
        DatabaseHandler databaseHandler=new DatabaseHandlerImpl();
        try{
            List<User> students=databaseHandler.getUsers(getStudents.getQueryStatement(),getCourse.getQueryStatement());
            students.forEach(student->{
                System.out.println(student.toString());
            });
        }catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
    }
    public void getStudent(GetUserRequest request){
        FileHandler getStudent=new FileHandlerImpl("SelectFromStudent.sql");
        FileHandler getCourse=new FileHandlerImpl("SelectFromCourse.sql");
        DatabaseHandler databaseHandler=new DatabaseHandlerImpl();
        try{
            User student=databaseHandler.getUser(getStudent.getQueryStatement(),getCourse.getQueryStatement(),request);
            student.toString();
        }catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
    }
}
