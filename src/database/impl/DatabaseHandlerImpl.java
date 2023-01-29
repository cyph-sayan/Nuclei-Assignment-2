package database.impl;

import java.util.List;

import database.DatabaseHandler;
import models.entities.User;
import models.requests.CreateUserRequest;
import models.requests.DeleteUserRequest;
import models.requests.ListUserRequest;

public class DatabaseHandlerImpl implements DatabaseHandler{

    //Create JDBC Connection Object here

    public DatabaseHandlerImpl() {
        //Initialize Connection Object by loading url, username and password from application.yaml/environment variables
    }

    @Override
    public User createUser(CreateUserRequest req) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<User> listUsers(ListUserRequest req) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void deleteUser(DeleteUserRequest req) {
        // TODO Auto-generated method stub
        
    }

}