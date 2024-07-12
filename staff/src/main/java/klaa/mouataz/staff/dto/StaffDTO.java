package klaa.mouataz.staff.dto;

import klaa.mouataz.staff.enumerations.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StaffDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private Gender gender;
}
