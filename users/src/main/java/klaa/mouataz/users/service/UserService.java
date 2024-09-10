package klaa.mouataz.users.service;

import klaa.mouataz.shared.page.PageResponse;
import klaa.mouataz.users.model.User;
import klaa.mouataz.users.repos.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {


    private final UserRepository userRepository;

public PageResponse<User> getUsers(int page, int size) {

    Pageable pageable= PageRequest.of(page, size);
    Page<User> users =userRepository.findAll(pageable);
    List<User> listUsers=users.toList();
    return new PageResponse<>(
            listUsers,
            users.getNumber(),
            users.getSize(),
            users.isFirst(),
            users.isLast()
    );
}

    public User getUser(UUID id) {
        return userRepository.findUserById(id);
    }

    public void deleteUser(UUID id) {
        userRepository.deleteById(id);
    }

    public User updateUser(UUID id, User user) {
        User existUser=userRepository.findUserById(id);
        existUser.setEmail(user.getEmail());
        existUser.setPassword(user.getPassword());
        return userRepository.save(existUser);
    }

}
