package klaa.mouataz.staff.repos;

import klaa.mouataz.staff.model.Staff;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StaffRepository extends JpaRepository<Staff,Long> {
    Staff findStaffById(Long id);
    Page<Staff> findAllStaffs(Pageable pageable);
}
