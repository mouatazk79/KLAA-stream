package klaa.mouataz.aggregator.demand;

import klaa.mouataz.shared.user.UserDto;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/aggregator/demands")
public class DemandController {
    private final DemandService demandService;
    @GetMapping
    public ResponseEntity<?> getLockedUsers(){
        return demandService.getLockedUsers();
    }
}
