package klaa.mouataz.staff.service.impl;

import klaa.mouataz.staff.model.Staff;
import klaa.mouataz.staff.repos.StaffRepository;
import klaa.mouataz.staff.service.StaffService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class StaffServiceImpl implements StaffService {
    private final StaffRepository staffRepository;
    @Override
    public Staff getStaffById(Long id) {
        return staffRepository.findStaffById(id);
    }

    @Override
    public List<Staff> getAllStaffs() {
        return staffRepository.findAll();
    }

    @Override
    public void deleteStaffById(Long id) {
        staffRepository.deleteById(id);
    }

    @Override
    public Staff updateStaffById(Long id, Staff staff) {
        staff.setId(id);
        return staffRepository.save(staff);
    }
}
