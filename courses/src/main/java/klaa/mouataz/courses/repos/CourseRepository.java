package klaa.mouataz.courses.repos;

import klaa.mouataz.courses.model.Course;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends ReactiveMongoRepository<Course,Long> {
}
