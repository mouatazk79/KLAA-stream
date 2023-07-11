package klaa.mouataz.courses.service;

import klaa.mouataz.courses.model.Course;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CourseService {
    Flux<Course> getCourses();
    Mono<Course> getCourse(Long id);
    Mono<Course> updateCourse(Long id,Course course);
    void deleteCourse(Long id);
    Mono<Course> addCourse(Course course);
}
