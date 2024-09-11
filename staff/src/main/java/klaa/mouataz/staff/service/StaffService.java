package klaa.mouataz.staff.service;

import klaa.mouataz.shared.page.PageResponse;
import klaa.mouataz.shared.staff.StaffDto;
import klaa.mouataz.staff.enumerations.Gender;
import klaa.mouataz.staff.model.Staff;
import klaa.mouataz.staff.repos.StaffRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class StaffService {
    private final StaffRepository staffRepository;
    public Staff getStaffById(Long id) {
        return staffRepository.findStaffById(id);
    }

    public void addStaff(StaffDto newStaff){
        Staff staff=Staff.builder()
                .firstName(newStaff.getFirstName())
                .lastName(newStaff.getLastName())
                .userName(newStaff.getUserName())
                .dateOfBirth(newStaff.getDateOfBirth())
                .phoneNumber(newStaff.getPhoneNumber())
                .gender(Gender.valueOf(newStaff.getGender()))
                .build();

         staffRepository.save(staff);
    }

    public PageResponse<Staff> getAllStaffs(int page, int size) {
        Pageable pageable= PageRequest.of(page, size);
        Page<Staff> staffs=staffRepository.findAll(pageable);
        List<Staff> staffList=staffs.toList();
        return new PageResponse<>(
                staffList,
                staffs.getNumber(),
                staffs.getSize(),
                staffs.isFirst(),
                staffs.isLast()

        );
    }
    public StaffDto getStaffByUserName(String userName){
        Staff staff=staffRepository.findStaffByUserName(userName).orElseThrow();
        StaffDto staffDto=StaffDto.builder()
                .firstName(staff.getFirstName())
                .lastName(staff.getLastName())
                .gender(staff.getGender().toString())
                .dateOfBirth(staff.getDateOfBirth())
                .phoneNumber(staff.getPhoneNumber())
                .build();
        return  staffDto;


    }

    public void deleteStaffById(Long id) {
        staffRepository.deleteById(id);
    }

    public Staff updateStaffById(Long id, Staff staff) {
        staff.setId(id);
        return staffRepository.save(staff);
    }
}
