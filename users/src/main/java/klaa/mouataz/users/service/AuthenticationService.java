package klaa.mouataz.users.service;

import klaa.mouataz.users.payload.AuthenticationRequest;
import klaa.mouataz.users.payload.AuthenticationResponse;
import klaa.mouataz.users.payload.RegisterRequest;

public interface AuthenticationService{
    AuthenticationResponse authenticate(AuthenticationRequest request);
    AuthenticationResponse register(RegisterRequest request);
}
