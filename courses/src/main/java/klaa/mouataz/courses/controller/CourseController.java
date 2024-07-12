package klaa.mouataz.courses.controller;


import klaa.mouataz.courses.model.Course;
import klaa.mouataz.courses.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/courses")
public class CourseController {
    private final CourseService courseService;
    @GetMapping
    public List<Course> getCourses(){
        return courseService.getCourses();
    }
    @GetMapping("/{courseId}")
    public Course getCourse(@PathVariable("courseId")Long id){
        return courseService.getCourse(id);
    }
    @PostMapping
    public Course addCourse(@RequestBody Course course){
        return courseService.addCourse(course);
    }
    @PatchMapping("/{id}")
    public Course updateCourse(@PathVariable("id") Long id,@RequestBody Course course){
        return courseService.updateCourse(id,course);
    }
    @DeleteMapping("/{courseId}")
    public void deleteCourse(@PathVariable("courseId")Long id){
         courseService.deleteCourse(id);
    }
}
