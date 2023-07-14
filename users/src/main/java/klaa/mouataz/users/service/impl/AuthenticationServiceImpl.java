package klaa.mouataz.users.service.impl;

import klaa.mouataz.users.config.JwtService;
import klaa.mouataz.users.model.User;
import klaa.mouataz.users.payload.AuthenticationRequest;
import klaa.mouataz.users.payload.AuthenticationResponse;
import klaa.mouataz.users.payload.RegisterRequest;
import klaa.mouataz.users.repos.UserRepository;
import klaa.mouataz.users.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.email(),request.password()
        ));
        var user=userRepository.findUserByEmail(request.email()).orElseThrow();
        var jwtToken=jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken).build();
    }

    @Override
    public AuthenticationResponse register(RegisterRequest request) {
        var user= User.builder()
                .email(request.email())
                .username(request.userName())
                .role(request.role())
                .password(passwordEncoder.encode(request.password()))
                .build();
        var jwtToken=jwtService.generateToken(user);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }
}
