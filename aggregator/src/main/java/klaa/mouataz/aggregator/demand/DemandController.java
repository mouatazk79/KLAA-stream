package klaa.mouataz.aggregator.demand;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/aggregator/demands")
public class DemandController {
    private final DemandService demandService;
    @GetMapping
    public ResponseEntity<?> getLockedUsers(@RequestHeader("Authorization") String bearerToken){
        return demandService.getLockedUsers(bearerToken);
    }
}
