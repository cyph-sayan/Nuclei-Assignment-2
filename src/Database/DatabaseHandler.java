package Database;
import models.entities.User;
import models.requests.CreateUserRequest;
import models.requests.DeleteUserRequest;
import models.requests.ListUserRequest;
import java.util.List;

public interface DatabaseHandler {
    void createUser(CreateUserRequest req);
    void deleteUser(DeleteUserRequest req);
    List<User> getUsers();
    User getUser(ListUserRequest req);
    void closeConnection();
}
