package klaa.mouataz.staff.repos;

import klaa.mouataz.staff.model.Staff;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface StaffRepository extends JpaRepository<Staff, Long> {
    Optional<Staff> findStaffById(Long id);
   Optional<Staff> findStaffByUserName(String userName);
}
