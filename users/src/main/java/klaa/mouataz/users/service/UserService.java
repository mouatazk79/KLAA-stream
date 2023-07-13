package klaa.mouataz.users.service;

import klaa.mouataz.users.model.User;

import java.util.List;
import java.util.UUID;

public interface UserService {
    List<User> getUsers();
    User getUser(UUID id);
    void  deleteUser(UUID id);
    User updateUser(UUID id,User user);
    User addUser(User user);

}
