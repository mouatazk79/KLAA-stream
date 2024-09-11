package klaa.mouataz.aggregator.register;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/aggregator/")
public class RegisterAggregatorController {
    private final RegisterService registerService;
    @PostMapping("signup")
    public ResponseEntity<?> register(@RequestBody RegistrationRequest register){
        // TODO: 9/10/2024 note database and add it to the request
        System.out.println("controller");
        registerService.register(register);
        return ResponseEntity.ok("user created");
    }
}
