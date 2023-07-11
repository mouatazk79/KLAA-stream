package klaa.mouataz.courses.service.impl;

import klaa.mouataz.courses.model.Course;
import klaa.mouataz.courses.repos.CourseRepository;
import klaa.mouataz.courses.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {
    private final CourseRepository courseRepository;
    @Override
    public Flux<Course> getCourses() {
        return courseRepository.findAll();
    }

    @Override
    public Mono<Course> getCourse(Long id) {
        return courseRepository.findById(id);
    }

    @Override
    public Mono<Course> updateCourse(Long id, Course course) {
        Mono<Course> existedCourse=courseRepository.findById(id);

        return null;
    }

    @Override
    public void deleteCourse(Long id) {
        courseRepository.deleteById(id);
    }

    @Override
    public Mono<Course> addCourse(Course course) {
        return courseRepository.save(course);
    }
}
