package klaa.mouataz.shared.user;

import klaa.mouataz.shared.DemandDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@FeignClient(name="user")
public interface UserClient {
    @PostMapping("/api/v1/auth/register")
    @ResponseStatus(HttpStatus.ACCEPTED)
     ResponseEntity<?> register(@RequestBody  RegisterRequest registerRequest);
    @GetMapping("/api/v1/users/locked")
     ResponseEntity<List<DemandDto> > getAllLockedUsers();
}
