package klaa.mouataz.courses.controller;


import klaa.mouataz.courses.model.Course;
import klaa.mouataz.courses.service.CourseService;
import klaa.mouataz.shared.page.PageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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
    public PageResponse<Course> getVisibleCourses( @RequestParam(name = "page", defaultValue = "0", required = false) int page,
                                                   @RequestParam(name = "size", defaultValue = "10", required = false) int size){
        return courseService.findAllVisibleCourses(page,size);
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
    @PostMapping(value = "/upload-cover/{courseId}",consumes = "multipart/form-data")
    public ResponseEntity<?> uploadBookCover(@PathVariable("courseId")String courseID, @RequestPart("file")MultipartFile file) throws IOException {

        courseService.uploadCourseCoverPicture(courseID,file);
        return ResponseEntity.accepted().build();
    }

}
