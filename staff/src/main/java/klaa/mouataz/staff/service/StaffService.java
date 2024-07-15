package klaa.mouataz.staff.service;

import klaa.mouataz.shared.page.PageResponse;
import klaa.mouataz.staff.model.Staff;
import klaa.mouataz.staff.repos.StaffRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class StaffService {
    private final StaffRepository staffRepository;
    public Staff getStaffById(Long id) {
        return staffRepository.findStaffById(id);
    }

    public PageResponse<Staff> getAllStaffs(int page, int size) {
        Pageable pageable= PageRequest.of(page, size);
        Page<Staff> staffs=staffRepository.findAllStaffs(pageable);
        List<Staff> staffList=staffs.toList();
        return new PageResponse<>(
                staffList,
                staffs.getNumber(),
                staffs.getSize(),
                staffs.isFirst(),
                staffs.isLast()

        );
    }

    public void deleteStaffById(Long id) {
        staffRepository.deleteById(id);
    }

    public Staff updateStaffById(Long id, Staff staff) {
        staff.setId(id);
        return staffRepository.save(staff);
    }
}
