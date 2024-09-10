package klaa.mouataz.shared.staff;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StaffDto {

    private String firstName;
    private String lastName;
    private String userName;
    private LocalDate dateOfBirth;
    private String phoneNumber;
    private String gender;
}
