package klaa.mouataz.users.service.impl;

import klaa.mouataz.users.model.User;
import klaa.mouataz.users.repos.UserRepository;
import klaa.mouataz.users.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUser(UUID id) {
        return userRepository.findUserById(id);
    }

    @Override
    public void deleteUser(UUID id) {
        userRepository.deleteById(id);
    }

    @Override
    public User updateUser(UUID id, User user) {
        User existUser=userRepository.findUserById(id);
        existUser.setEmail(user.getEmail());
        existUser.setPassword(user.getPassword());
        return userRepository.save(existUser);
    }

    @Override
    public User addUser(User user) {
        return userRepository.save(user);
    }
}
