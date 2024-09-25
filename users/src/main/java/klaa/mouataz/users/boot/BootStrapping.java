package klaa.mouataz.users.boot;

import klaa.mouataz.users.enumerations.Role;
import klaa.mouataz.users.model.User;
import klaa.mouataz.users.repos.RoleRepository;
import klaa.mouataz.users.repos.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BootStrapping implements CommandLineRunner {
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    @Override
    public void run(String... args) throws Exception {
        if (roleRepository.findByName("ADMIN").isEmpty()){
            Role role=Role.builder()
                    .name("ADMIN").build();
            roleRepository.save(role);
        }
        if (roleRepository.findByName("USER").isEmpty()){
            Role role=Role.builder()
                    .name("USER").build();
            roleRepository.save(role);
        }
        if (userRepository.findByEmail("admin@klaa.com").isEmpty()){
            User admin=User.builder()
                            .username("admin@klaa.com")
                            .email("admin@klaa.com")
                                    .roles(roleRepository.findByName("ADMIN").stream().toList())
                                            .accountLocked(false)
                                                    .enabled(true)
                                                            .password(encoder.encode("admin0123"))
                                                                    .build();
            userRepository.save(admin);
        }



    }
}
