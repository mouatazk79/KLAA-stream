package klaa.mouataz.student.controller;

import klaa.mouataz.student.model.Student;
import klaa.mouataz.student.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/api/v1/students")
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;
    @GetMapping
    public List<Student> getStudents(){
        return studentService.getStudents();
    }
    @GetMapping("/{studentId}")
    public Student getStudent(@PathVariable("studentId")Long id){
        return studentService.getStudent(id);
    }
    @PutMapping("/{studentId}")
    public Student updateStudent(@PathVariable("studentId")Long id,@RequestBody Student student){
        return studentService.updateStudent(id,student);
    }
    @PostMapping
    public Student addStudent(@RequestBody Student student){
     return    studentService.addStudent(student);
    }
    @DeleteMapping("{studentId}")
    public void deleteStudent(@PathVariable("studentId")Long id){
        studentService.deleteStudent(id);
    }


}
