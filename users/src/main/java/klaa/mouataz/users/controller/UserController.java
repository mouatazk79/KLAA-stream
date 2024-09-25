package klaa.mouataz.users.controller;

import klaa.mouataz.shared.DemandDto;
import klaa.mouataz.shared.page.PageResponse;

import klaa.mouataz.users.model.User;
import klaa.mouataz.users.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserService userService;
    private final UserDetailsService userDetailsService;

    @GetMapping
    public PageResponse<User> getAllUsers(
            @RequestParam(name = "page", defaultValue = "0", required = false) int page,
            @RequestParam(name = "size", defaultValue = "10", required = false) int size
    ) {
        return userService.getUsers(page, size);
    }

    @GetMapping("/locked")
    public ResponseEntity<List<DemandDto>> getAllLockedUsers() {
        List<DemandDto> lockedUsers = userService.getLockedUsers();
        return ResponseEntity.ok(lockedUsers);
    }

    @GetMapping("/{userName}")
    public User getUserByUserName(@PathVariable("userName") String userName) {
        return (User) userDetailsService.loadUserByUsername(userName);
    }
    @GetMapping("/{userId}")
    public User getUser(@PathVariable("userId") UUID id) {
        return userService.getUser(id);
    }

    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable("userId") UUID id) {
        userService.deleteUser(id);
    }

    @PutMapping("/{userName}")
    public void activate(@PathVariable("userName") String userName) {
        // TODO: 9/20/2024  send email and sms 
        User user = (User) userDetailsService.loadUserByUsername(userName);
        user.setEnabled(true);
        user.setAccountLocked(false);
        userService.saveUser(user);
    }
    @PutMapping("/lock/{userName}")
    public void lock(@PathVariable("userName") String userName) {
        User user = (User) userDetailsService.loadUserByUsername(userName);

        user.setAccountLocked(!user.isAccountLocked());
        userService.saveUser(user);
    }
}