package klaa.mouataz.courses.controller;

import klaa.mouataz.courses.model.Course;
import klaa.mouataz.courses.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/courses")
public class CourseController {
    private final CourseService courseService;
    @GetMapping
    public Flux<Course> getCourses(){
        return courseService.getCourses();
    }
    @GetMapping("/{courseId}")
    public Mono<Course> getCourse(@PathVariable("courseId")Long id){
        return courseService.getCourse(id);
    }
    @PostMapping
    public Mono<Course> addCourse(@RequestBody Course course){
        Course newCourse=Course.builder()
                .id(1L)
                .name("ccna")
                .teacherId(1L)
                .field("networking")
                .description("hhhhhh")
                .build();
        return courseService.addCourse(newCourse);
    }
    @DeleteMapping("/{courseId}")
    public void deleteCourse(@PathVariable("courseId")Long id){
         courseService.deleteCourse(id);
    }
}
