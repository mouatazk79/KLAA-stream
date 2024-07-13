package klaa.mouataz.courses.service.impl;

import klaa.mouataz.courses.model.Course;
import klaa.mouataz.courses.repos.CourseRepository;
import klaa.mouataz.courses.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {
    private final CourseRepository courseRepository;
    private final KafkaTemplate<String,Object> kafkaTemplate;
    private final NewTopic topic;


    @Override
    public List<Course> getCourses() {
        return courseRepository.findAll();
    }

    @Override
    public Course getCourse(String id) {
        return courseRepository.findCourseById(id);
    }

    @Override
    public Course updateCourse(String id, Course course) {
        Course existedCourse=courseRepository.findCourseById(id);
        return courseRepository.save(existedCourse);
    }

    @Override
    public void deleteCourse(String id) {
        courseRepository.deleteById(id);

    }

    @Override
    public Course addCourse(Course course) {

        kafkaTemplate.send(topic.name(),course);
        return courseRepository.save(course);
    }
}
