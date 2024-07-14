package klaa.mouataz.staff.service;

import klaa.mouataz.staff.model.Staff;
import klaa.mouataz.staff.repos.StaffRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class StaffService {
    private final StaffRepository staffRepository;
    public Staff getStaffById(Long id) {
        return staffRepository.findStaffById(id);
    }

    public List<Staff> getAllStaffs() {
        return staffRepository.findAll();
    }

    public void deleteStaffById(Long id) {
        staffRepository.deleteById(id);
    }

    public Staff updateStaffById(Long id, Staff staff) {
        staff.setId(id);
        return staffRepository.save(staff);
    }
}
