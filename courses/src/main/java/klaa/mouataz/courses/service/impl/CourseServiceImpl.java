package klaa.mouataz.courses.service.impl;

import klaa.mouataz.courses.model.Course;
import klaa.mouataz.courses.repos.CourseRepository;
import klaa.mouataz.courses.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {
    private final CourseRepository courseRepository;


    @Override
    public List<Course> getCourses() {
        return courseRepository.findAll();
    }

    @Override
    public Course getCourse(Long id) {
        return courseRepository.findCourseById(id);
    }

    @Override
    public Course updateCourse(Long id, Course course) {
        Course existedCourse=courseRepository.findCourseById(id);
        return courseRepository.save(existedCourse);
    }

    @Override
    public void deleteCourse(Long id) {
        courseRepository.deleteById(id);

    }

    @Override
    public Course addCourse(Course course) {
        return courseRepository.save(course);
    }
}
