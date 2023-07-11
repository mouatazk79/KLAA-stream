package klaa.mouataz.teacher.repos;

import klaa.mouataz.teacher.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher,Long> {
    Teacher getTeacherById(Long id);
}
