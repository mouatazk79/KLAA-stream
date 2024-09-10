package klaa.mouataz.aggregator.register;

import lombok.AllArgsConstructor;
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
    public void register(@RequestBody RegistrationRequest register){
        System.out.println("controller");
        registerService.register(register);
    }
}
