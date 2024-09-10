package klaa.mouataz.shared.user;

import lombok.Builder;

@Builder
public record RegisterRequest(String userName, String role, String email, String password) {
}
