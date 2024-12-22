package klaa.mouataz.staff.service;

import klaa.mouataz.shared.page.PageResponse;
import klaa.mouataz.shared.staff.StaffDto;
import klaa.mouataz.staff.enumerations.Gender;
import klaa.mouataz.staff.exception.StaffNotFoundException;
import klaa.mouataz.staff.model.Staff;
import klaa.mouataz.staff.repos.StaffRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
@AllArgsConstructor
public class StaffService {
    private final StaffRepository staffRepository;
    public Staff getStaffById(Long id) {
        Optional<Staff> staff=staffRepository.findStaffById(id);
        if(staff.isEmpty()){
            log.error("staff not found with id: {}",id);
            throw new StaffNotFoundException("staff not found with id:"+id);
        }
        return staff.get();
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
        log.info("staff {} added",newStaff);

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
        Optional<Staff> staff=staffRepository.findStaffByUserName(userName);
        if(staff.isEmpty()){
            log.error("staff not found with userName: {}",userName);
            throw new StaffNotFoundException("staff not found with userName:"+userName);
        }
        return StaffDto.builder()
                .firstName(staff.get().getFirstName())
                .lastName(staff.get().getLastName())
                .fullName(staff.get().getFullName())
                .gender(staff.get().getGender().toString())
                .dateOfBirth(staff.get().getDateOfBirth())
                .phoneNumber(staff.get().getPhoneNumber())
                .build();

    }

    public void deleteStaffById(Long id) {
        log.info("notification with id: {} deleted",id);
        staffRepository.deleteById(id);
    }

    public Staff updateStaffById(Long id, Staff staff) {
        staff.setId(id);
        return staffRepository.save(staff);
    }
}
