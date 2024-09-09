package klaa.mouataz.courses.service;

import klaa.mouataz.courses.model.Course;
import klaa.mouataz.courses.repos.CourseRepository;
import klaa.mouataz.courses.util.FileStorageService;
import klaa.mouataz.shared.CoursePayload;
import klaa.mouataz.shared.page.PageResponse;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseService {
    private final CourseRepository courseRepository;
    private final KafkaTemplate<String,Object> kafkaTemplate;
    private final NewTopic topic;
    private final FileStorageService fileStorageService;



    public List<Course> getCourses() {
        return courseRepository.findAll();
    }

    public List<Course> findTeacherCourses(Long id) {

        return courseRepository.findCoursesByTeacherId(id);
    }

   public PageResponse<Course> findAllVisibleCourses(int page,int size){

       Pageable pageable= PageRequest.of(page,size);

       Page<Course> courses=courseRepository.findCoursesByVisibleIsTrue(pageable);
       List<Course> coursesList=courses.toList();
       return new PageResponse<>(
               coursesList,
               courses.getNumber(),
               courses.getSize(),
               courses.isFirst(),
               courses.isLast()
       );
    }


    public Course getCourse(String id) {
        return courseRepository.findCourseById(id);
    }


    public Course updateCourse(String id, Course course) {
        Course existedCourse=courseRepository.findCourseById(id);
        return courseRepository.save(existedCourse);
    }


    public void deleteCourse(String id) {
        courseRepository.deleteById(id);

    }


    public Course addCourse(Course course) {

        CoursePayload coursePayload=CoursePayload.builder()
                        .name(course.getName())
                                .teacherId(course.getTeacherId())
                                        .courseURL(course.getImageURL()).build();

        kafkaTemplate.send(topic.name(),coursePayload);
        return courseRepository.save(course);
    }

    @Transactional
    public void uploadCourseCoverPicture(String courseID, MultipartFile file) throws IOException {
        Course course=courseRepository.findCourseById(courseID);
        var bookCover=fileStorageService.saveFile(file,course);
        course.setImageURL(bookCover);
        courseRepository.save(course);

    }
}
