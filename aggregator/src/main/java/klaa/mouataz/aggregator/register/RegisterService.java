package klaa.mouataz.aggregator.register;

import klaa.mouataz.shared.staff.StaffClient;
import klaa.mouataz.shared.staff.StaffDto;
import klaa.mouataz.shared.user.RegisterRequest;
import klaa.mouataz.shared.user.UserClient;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class RegisterService {
    private final StaffClient staffClient;
    private final UserClient userClient;

    public ResponseEntity<?> register( RegistrationRequest register){
        System.out.println("service");

        RegisterRequest registerRequest=RegisterRequest.builder()
                .email(register.getEmail())
                .userName(register.getUserName())
                .password(register.getPhoneNumber())
                .role(register.getRole())
                .build();
        userClient.register(registerRequest);
       StaffDto staffDto= StaffDto.builder()
                .firstName(register.getFirstName())
               .lastName(register.getLastName())
               .dateOfBirth(register.getDateOfBirth())
               .gender(register.getGender())
               .userName(register.getUserName())
               .phoneNumber(register.getPhoneNumber())
               .build();
       staffClient.addStaff(staffDto);
       return   ResponseEntity.ok("stay ");




    }

}
