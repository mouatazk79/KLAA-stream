package klaa.mouataz.courses.service;

import klaa.mouataz.courses.model.Course;
import java.util.List;

public interface CourseService {
    List<Course> getCourses();
    Course getCourse(String id);
    Course updateCourse(String id,Course course);
    void deleteCourse(String id);
    Course addCourse(Course course);
}
