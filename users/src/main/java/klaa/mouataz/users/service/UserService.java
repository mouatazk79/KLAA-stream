package klaa.mouataz.users.service;

import klaa.mouataz.shared.DemandDto;
import klaa.mouataz.shared.page.PageResponse;
import klaa.mouataz.shared.staff.StaffClient;
import klaa.mouataz.shared.staff.StaffDto;
import klaa.mouataz.shared.user.UserDto;
import klaa.mouataz.users.model.User;
import klaa.mouataz.users.repos.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {


    private final UserRepository userRepository;
    private final StaffClient staffClient;

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

    public List<DemandDto> getLockedUsers(){
    List<UserDto> userDtos= userRepository.findUsersByAccountLockedTrueAndEnabledFalse().stream().map(
            user -> new UserDto(user.getEmail(), user.getUsername())
    ).toList();

        return userDtos.stream().map(
                userDto -> toDemandDto(userDto,staffClient.getStaffByUserName(userDto.getUsername()))
        ).collect(Collectors.toList());
    }

    private DemandDto toDemandDto(UserDto userDto, StaffDto staffDto){

        DemandDto demandDto = new DemandDto();
        demandDto.setUserName(userDto.getUsername());
        demandDto.setEmail(userDto.getEmail());
        demandDto.setFirstName(staffDto.getFirstName());
        demandDto.setLastName(staffDto.getLastName());
        demandDto.setDateOfBirth(staffDto.getDateOfBirth());
        demandDto.setPhoneNumber(staffDto.getPhoneNumber());
        demandDto.setGender(staffDto.getGender());

        return demandDto;
    }

    public void saveUser(User user){
    userRepository.save(user);
    }
}
