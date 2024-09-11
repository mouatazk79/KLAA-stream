package klaa.mouataz.users.controller;

import klaa.mouataz.shared.DemandDto;
import klaa.mouataz.shared.page.PageResponse;
import klaa.mouataz.shared.user.UserDto;
import klaa.mouataz.users.model.User;
import klaa.mouataz.users.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserService userService;

@GetMapping
public PageResponse<User> getAllUsers(
        @RequestParam(name = "page", defaultValue = "0", required = false) int page,
        @RequestParam(name = "size", defaultValue = "10", required = false) int size
){
    return userService.getUsers(page, size);
}
    @GetMapping("/locked")
    public ResponseEntity<List<DemandDto> > getAllLockedUsers(){
        List<DemandDto>  lockedUsers=userService.getLockedUsers();
        return ResponseEntity.ok(lockedUsers);
    }
    @GetMapping("/{userId}")
    public User getUser(@PathVariable("userId")UUID id){
        return userService.getUser(id);
    }
    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable("userId")UUID id){
         userService.deleteUser(id);
    }


}
