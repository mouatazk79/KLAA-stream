package klaa.mouataz.courses.service;

import klaa.mouataz.courses.model.Course;
import java.util.List;

public interface CourseService {
    List<Course> getCourses();
    Course getCourse(Long id);
    Course updateCourse(Long id,Course course);
    void deleteCourse(Long id);
    Course addCourse(Course course);
}
