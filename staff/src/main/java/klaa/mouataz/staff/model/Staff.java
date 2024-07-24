package klaa.mouataz.staff.model;

import jakarta.persistence.*;
import klaa.mouataz.staff.enumerations.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.Period;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Staff {
    @Id
    @SequenceGenerator(name = "staffID",allocationSize = 1,sequenceName = "staffID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "staffID")
    private Long id;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String phoneNumber;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @Transient
    private int age;
    public int getAge() {
        return Period.between(dateOfBirth, LocalDate.now()).getYears();
    }
    @Transient
    private String fullName;
    public String getFullName() {
        return firstName + " " + lastName;
    }


}
