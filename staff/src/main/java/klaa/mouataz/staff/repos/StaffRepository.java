package klaa.mouataz.staff.repos;

import klaa.mouataz.staff.model.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StaffRepository extends JpaRepository<Staff,Long> {
    Staff findStaffById(Long id);
}
