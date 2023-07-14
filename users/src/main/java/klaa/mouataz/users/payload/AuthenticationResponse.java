package klaa.mouataz.users.payload;

import lombok.Builder;

@Builder
public record AuthenticationResponse(String token) {
}
