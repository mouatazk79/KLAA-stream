package klaa.mouataz.users.payload;

import klaa.mouataz.users.enumerations.Role;

public record RegisterRequest(String userName, String role, String email, String password) {
}
