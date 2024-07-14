package klaa.mouataz.courses.repos;

import klaa.mouataz.courses.model.Course;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends MongoRepository<Course,String> {
    Course findCourseById(String id);
    List<Course> findCoursesByTeacherId(Long id);
    List<Course> findCoursesByVisibleIsTrue();
}
