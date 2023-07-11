package klaa.mouataz.courses.repos;

import klaa.mouataz.courses.model.Course;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends R2dbcRepository<Course,Long> {
}
