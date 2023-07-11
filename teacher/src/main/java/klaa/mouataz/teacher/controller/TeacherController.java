package klaa.mouataz.teacher.controller;

import klaa.mouataz.teacher.model.Teacher;
import klaa.mouataz.teacher.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/teachers")
public class TeacherController {
    private final TeacherService teacherService;
    @GetMapping
    public List<Teacher> getTeachers(){
        return teacherService.getTeachers();
    }
    @GetMapping("/{teacherId}")
    public Teacher getTeacher(@PathVariable("teacherId")Long id){
        return teacherService.getTeacher(id);
    }
    @PutMapping("/{teacherId}")
    public Teacher updateTeacher(@PathVariable("teacherId")Long id,@RequestBody Teacher teacher){
        return teacherService.updateTeacher(id,teacher);
    }
    @PostMapping
    public Teacher addTeacher(@RequestBody Teacher teacher){
        return    teacherService.addTeacher(teacher);
    }
    @DeleteMapping("{teacherId}")
    public void deleteTeacher(@PathVariable("teacherId")Long id){
        teacherService.deleteTeacher(id);
    }

}
