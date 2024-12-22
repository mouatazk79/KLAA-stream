package klaa.mouataz.courses.repos;

import klaa.mouataz.courses.model.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourseRepository extends MongoRepository<Course,String> {
    Optional<Course> findCourseById(String id);
    Course findCourseByName(String name);
    List<Course> findCoursesByTeacherId(Long id);
    Page<Course> findCoursesByVisibleIsTrue(Pageable pageable);

}
