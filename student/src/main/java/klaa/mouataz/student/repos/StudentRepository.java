package klaa.mouataz.student.repos;

import klaa.mouataz.student.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {
    Student getStudentById(Long id);
}
