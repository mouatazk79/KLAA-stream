package klaa.mouataz.staff.model;

import jakarta.persistence.*;
import klaa.mouataz.staff.enumerations.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Staff {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String firstName;
    private String lastName;
    @Transient
    private String fullName;
    @Enumerated(EnumType.STRING)
    private Gender gender;

    public String getFullName() {
        return firstName + " " + lastName;
    }


}
