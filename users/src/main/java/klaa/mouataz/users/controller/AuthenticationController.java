package klaa.mouataz.users.controller;

import jakarta.validation.Valid;
import klaa.mouataz.users.payload.AuthenticationRequest;
import klaa.mouataz.users.payload.AuthenticationResponse;
import klaa.mouataz.shared.user.RegisterRequest;
import klaa.mouataz.users.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth/")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    @PostMapping("register")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<?> register(@RequestBody @Valid RegisterRequest registerRequest){
        authenticationService.register(registerRequest);
        return ResponseEntity.accepted().build();
    }
    @PostMapping("authenticate")
    public ResponseEntity<AuthenticationResponse> auth(@RequestBody @Valid AuthenticationRequest authenticationRequest){
        return ResponseEntity.ok(authenticationService.authenticate(authenticationRequest));
    }

}
