package klaa.mouataz.courses.controller;


import jakarta.servlet.http.HttpServletRequest;
import klaa.mouataz.courses.model.Course;
import klaa.mouataz.courses.service.CourseService;
import klaa.mouataz.shared.page.PageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.HandlerMapping;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/courses")
public class CourseController {
    private final ResourceLoader resourceLoader;
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
                                                   @RequestParam(name = "size", defaultValue = "100", required = false) int size){
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
    @PostMapping(value = "/upload-cover/{courseId}",consumes =MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> uploadCourseCover(@PathVariable("courseId")String courseID, @RequestPart("file")MultipartFile file) throws IOException {

        System.out.println(file);
        courseService.uploadCourseCoverPicture(courseID,file);
        return ResponseEntity.accepted().build();
    }

    @GetMapping("/image/**")
    public ResponseEntity<Resource> getImage(HttpServletRequest request) {
        // Retrieve the relative path from the request
        String path = (String) request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
        String bestMatchPattern = (String) request.getAttribute(HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE);

        // Remove the pattern from the path to get the relative path
        String relativePath = path.replaceFirst(bestMatchPattern.replace("/**", ""), "");

        try {
            // Load the resource using the relative path
            Resource resource = resourceLoader.getResource("classpath:" + relativePath);

            if (resource.exists() && resource.isReadable()) {
                // Determine the content type dynamically
                String contentType = Files.probeContentType(Paths.get(resource.getURI()));

                // Fallback to a default content type if unknown
                if (contentType == null) {
                    contentType = MimeTypeUtils.APPLICATION_OCTET_STREAM_VALUE;
                }

                return ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_TYPE, contentType)
                        .body(resource);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } catch (IOException e) {
            // Handle IO exceptions (e.g., file not found, reading error)
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
