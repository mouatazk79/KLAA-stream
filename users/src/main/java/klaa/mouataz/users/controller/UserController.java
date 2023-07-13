package klaa.mouataz.users.controller;

import klaa.mouataz.users.model.User;
import klaa.mouataz.users.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserService userService;
    @GetMapping
    public List<User> getUsers(){
        return userService.getUsers();
    }
    @GetMapping("/{userId}")
    public User getUser(@PathVariable("userId")UUID id){
        return userService.getUser(id);
    }
    @PostMapping
    public User getUser(@RequestBody User user){
        return userService.addUser(user);
    }
    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable("userId")UUID id){
         userService.deleteUser(id);
    }


}
