package klaa.mouataz.teacher.service;

import klaa.mouataz.teacher.model.Teacher;

import java.util.List;

public interface TeacherService {
    List<Teacher> getTeachers();
    Teacher getTeacher(Long id);
    void  deleteTeacher(Long id);
    Teacher updateTeacher(Long id,Teacher teacher);
    Teacher addTeacher(Teacher teacher);
}
