package klaa.mouataz.courses.controller;


import klaa.mouataz.courses.model.Course;
import klaa.mouataz.courses.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.IOException;

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
        return courseService.addCourse(course);
    }
    @DeleteMapping("/{courseId}")
    public void deleteCourse(@PathVariable("courseId")Long id){
         courseService.deleteCourse(id);
    }
}
