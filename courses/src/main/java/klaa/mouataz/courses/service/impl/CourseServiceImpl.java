package klaa.mouataz.courses.service.impl;

import klaa.mouataz.courses.model.Course;
import klaa.mouataz.courses.repos.CourseRepository;
import klaa.mouataz.courses.service.CourseService;
import klaa.mouataz.rbmqp.RabbitMQProducer;
import klaa.mouataz.shared.notification.Notification;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {
    private final CourseRepository courseRepository;
    private final RabbitMQProducer rabbitMQProducer;
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
        Notification notification=Notification.builder()
                .id(44L)
                .subject("new course")
                .description("new course")
                .build();
        rabbitMQProducer.publish(notification,"internal.exchange","internal.notification.routing-key");
        return courseRepository.save(course);
    }
}
