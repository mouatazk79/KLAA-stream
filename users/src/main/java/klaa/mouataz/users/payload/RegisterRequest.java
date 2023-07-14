package klaa.mouataz.users.payload;

import klaa.mouataz.users.enumerations.Role;

public record RegisterRequest(String firstName, String lastName, String userName, Role role, String email, String password) {
}
