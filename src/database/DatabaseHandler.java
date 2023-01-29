package database;
import java.util.List;

import models.entities.User;
import models.requests.CreateUserRequest;
import models.requests.DeleteUserRequest;
import models.requests.ListUserRequest;

public interface DatabaseHandler {
    User createUser(CreateUserRequest req);
    List<User> listUsers(ListUserRequest req);
    void deleteUser(DeleteUserRequest req);
}
