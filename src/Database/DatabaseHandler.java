package Database;
import models.entities.User;
import models.requests.CreateUserRequest;
import models.requests.DeleteUserRequest;
import models.requests.ListUserRequest;

public interface DatabaseHandler {
    void createUser(CreateUserRequest req);
    void deleteUser(DeleteUserRequest req);
    User listUser(ListUserRequest req);
    void closeConnection();
}
