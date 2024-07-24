package klaa.mouataz.users.service;

import klaa.mouataz.users.enumerations.Role;
import klaa.mouataz.users.model.Token;
import klaa.mouataz.users.model.User;
import klaa.mouataz.users.payload.AuthenticationRequest;
import klaa.mouataz.users.payload.AuthenticationResponse;
import klaa.mouataz.users.payload.RegisterRequest;
import klaa.mouataz.users.repos.RoleRepository;
import klaa.mouataz.users.repos.TokenRepository;
import klaa.mouataz.users.repos.UserRepository;
import klaa.mouataz.users.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @Transactional
    public void register(RegisterRequest registerRequest) {
        Role userRole=roleRepository.findByName(registerRequest.role())
                .orElseThrow();
        var user= User.builder()
                .username(registerRequest.userName())
                .email(registerRequest.email())
                .password(passwordEncoder.encode(registerRequest.password()))
                .accountLocked(false)
                .enabled(true)
                .roles(List.of(userRole))
                .build();
        userRepository.save(user);


    }



    public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest) {
        var auth =authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.email(),
                        authenticationRequest.password()
                )
        );
        var claims=new HashMap<String,Object>();
        var user=((User)auth.getPrincipal());
        claims.put("username",user.getUsername());
        var jwtToken=jwtService.generateToken(claims,user);
        return AuthenticationResponse.builder()
                .token(jwtToken).build();

    }
}
