package klaa.mouataz.aggregator.register;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationRequest {
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String phoneNumber;
    private String gender;
    private String userName;
    private String role;
    private String email;

}
