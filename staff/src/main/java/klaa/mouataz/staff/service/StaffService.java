package klaa.mouataz.staff.service;

import klaa.mouataz.staff.model.Staff;

import java.util.List;

public interface StaffService {
    Staff getStaffById(Long id);
    List<Staff> getAllStaffs();
    void deleteStaffById(Long id);
    Staff updateStaffById(Long id,Staff staff);
}
