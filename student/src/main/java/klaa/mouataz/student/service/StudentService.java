package klaa.mouataz.student.service;

import klaa.mouataz.student.model.Student;

import java.util.List;

public interface StudentService {
    List<Student> getStudents();
    Student getStudent(Long id);
    void  deleteStudent(Long id);
    Student updateStudent(Student student);
}
