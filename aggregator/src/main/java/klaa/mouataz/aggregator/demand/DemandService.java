package klaa.mouataz.aggregator.demand;

import klaa.mouataz.shared.DemandDto;
import klaa.mouataz.shared.user.UserClient;
import klaa.mouataz.shared.user.UserDto;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DemandService {
    private final UserClient userClient;

    public ResponseEntity<List<DemandDto> > getLockedUsers(){
        return userClient.getAllLockedUsers();
    }
}
