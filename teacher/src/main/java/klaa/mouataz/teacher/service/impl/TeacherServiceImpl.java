package klaa.mouataz.teacher.service.impl;

import klaa.mouataz.teacher.model.Teacher;
import klaa.mouataz.teacher.repos.TeacherRepository;
import klaa.mouataz.teacher.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class TeacherServiceImpl implements TeacherService {
    private final TeacherRepository teacherRepository;
    @Override
    public List<Teacher> getTeachers() {
        return teacherRepository.findAll();
    }

    @Override
    public Teacher getTeacher(Long id) {
        return teacherRepository.getTeacherById(id);
    }

    @Override
    public void deleteTeacher(Long id) {
        teacherRepository.deleteById(id);
    }

    @Override
    public Teacher updateTeacher(Long id, Teacher teacher) {
        Teacher existTeacher=teacherRepository.getTeacherById(id);
        existTeacher.setFirstName(teacher.getFirstName());
        existTeacher.setLastName(teacher.getLastName());
        return teacherRepository.save(existTeacher);
    }

    @Override
    public Teacher addTeacher(Teacher teacher) {
        return teacherRepository.save(teacher);
    }
}
