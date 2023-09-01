package klaa.mouataz.student.service.impl;

import klaa.mouataz.clients.fraud.Notification;
import klaa.mouataz.student.model.Student;
import klaa.mouataz.student.repos.StudentRepository;
import klaa.mouataz.student.service.StudentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
@Slf4j
@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;
    private final RestTemplate restTemplate;
    @Override
    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Student getStudent(Long id) {
        return studentRepository.getStudentById(id);
    }

    @Override
    public void deleteStudent(Long id) {
        log.info("student deleted {}",id);
        studentRepository.deleteById(id);
    }

    @Override
    public Student updateStudent(Long id,Student student) {
        Student existedStudent=studentRepository.getStudentById(id);
        existedStudent.setFirstName(student.getFirstName());
        existedStudent.setLastName(student.getLastName());
        return studentRepository.save(existedStudent);
    }

    @Override
    public Student addStudent(Student student) {
        restTemplate.getForObject(
                "localhost:NOTIFICATION/api/v1/notifications"
                , Notification.class
        );
        return studentRepository.save(student);
    }
}
