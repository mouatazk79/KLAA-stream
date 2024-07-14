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
//    @GetMapping
//    public List<Course> getCourses(){
//        return courseService.getCourses();
//    }
    @GetMapping("/teacher/{teacherId}")
    public List<Course> getTeacherCourses(@PathVariable("teacherId")Long id){
        return courseService.findTeacherCourses(id);
    }
    @GetMapping
    public List<Course> getVisibleCourses(){
        return courseService.findAllVisibleCourses();
    }
    @GetMapping("/{courseId}")
    public Course getCourse(@PathVariable("courseId")String id){
        return courseService.getCourse(id);
    }
    @PostMapping
    public Course addCourse(@RequestBody Course course){
        return courseService.addCourse(course);
    }
    @PatchMapping("/{id}")
    public Course updateCourse(@PathVariable("id") String id,@RequestBody Course course){
        return courseService.updateCourse(id,course);
    }
    @DeleteMapping("/{courseId}")
    public void deleteCourse(@PathVariable("courseId")String id){
         courseService.deleteCourse(id);
    }
}
