package klaa.mouataz.courses.service;

import klaa.mouataz.courses.model.Course;
import klaa.mouataz.courses.repos.CourseRepository;
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
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseService {
    private static final String UPLOAD_DIRECTORY = "Z:/springprojects/KLAAschool/courses/src/main/resources/images";

    private final CourseRepository courseRepository;
    private final KafkaTemplate<String,Object> kafkaTemplate;
    private final NewTopic topic;



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
                                .build();

        kafkaTemplate.send(topic.name(),coursePayload);
        return courseRepository.save(course);
    }

    @Transactional
    public void uploadCourseCoverPicture(String courseID, MultipartFile file) throws IOException {
        Course course=courseRepository.findCourseByName(courseID);
        String time= String.valueOf(System.currentTimeMillis());
        String fileName = file.getOriginalFilename();
        fileName=time+fileName;
        Path filePath = Paths.get(UPLOAD_DIRECTORY, fileName);
        Files.createDirectories(filePath.getParent());
        file.transferTo(filePath.toFile());
        String imageUrl = "/images/"+fileName;
        course.setImageURL(imageUrl);
        courseRepository.save(course);
    }
}
