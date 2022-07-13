package my.ah.school_be.service;

import my.ah.school_be.entity.CourseEntity;
import my.ah.school_be.entity.EnrollEntity;
import my.ah.school_be.entity.StudentEntity;
import my.ah.school_be.repository.CourseRepository;
import my.ah.school_be.repository.EnrollRepository;
import my.ah.school_be.repository.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Service
public class EnrollService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    private Environment env;

    @Autowired
    EnrollRepository enrollRepository;

    public List<EnrollEntity> findAll() {
        return enrollRepository.findAll();
    }


    public List<EnrollEntity> findByStudent(StudentEntity student) {
        List<EnrollEntity> searchEntity = (List<EnrollEntity>) enrollRepository.findByStudent(student);
        return searchEntity;
    }

    public List<EnrollEntity> findByCourse(CourseEntity course) {
        List<EnrollEntity> searchEntity = (List<EnrollEntity>) enrollRepository.findByCourse(course);
        return searchEntity;
    }

    public EnrollEntity save(Long studentId, Long courseId, EnrollEntity enrollment) {
        System.out.println("\n" + enrollment.getCourse() + "\n");

        Optional<StudentEntity> schStudent = studentRepository.findById(studentId);
        if (!schStudent.isPresent()){
            throw new EntityNotFoundException("student id " + studentId + " does not exist in record");
        }

        Optional<CourseEntity> schCourse = courseRepository.findById(courseId);
        if (!schCourse.isPresent()){
            throw new EntityNotFoundException("course id " + schCourse + " does not exist in record");
        }

        EnrollEntity enroll = new EnrollEntity();
        studentRepository.findById(studentId).map((Function<StudentEntity, Object>) enroll::setStudent);
        courseRepository.findById(courseId).map((Function<CourseEntity, Object>) enroll::setCourse);
        enroll.setYearTaken(enrollment.getYearTaken());
        enroll.setScore(enrollment.getScore());

        return enrollRepository.save(enroll);
    }

    public EnrollEntity updateYear(StudentEntity studentId, CourseEntity courseId, EnrollEntity enrollment) {
        EnrollEntity enrollEntity;
        Optional<EnrollEntity> searchEntity = enrollRepository.findByStudentAndCourse(studentId,courseId);
        if (searchEntity.isPresent()){
            EnrollEntity searchResult = searchEntity.get();
            searchResult.setYearTaken(enrollment.getYearTaken());
            enrollEntity = enrollRepository.save(searchResult);
        } else {
            throw new EntityNotFoundException();
        }
        return enrollEntity;
    }

    public EnrollEntity updateScore(StudentEntity studentId, CourseEntity courseId, EnrollEntity enrollment) {
        EnrollEntity enrollEntity;
        Optional<EnrollEntity> searchEntity = enrollRepository.findByStudentAndCourse(studentId,courseId);
        if (searchEntity.isPresent()){
            EnrollEntity searchResult = searchEntity.get();
            searchResult.setScore(enrollment.getScore());
            enrollEntity = enrollRepository.save(searchResult);
        } else {
            throw new EntityNotFoundException();
        }
        return enrollEntity;
    }

    public EnrollEntity update(Long id, EnrollEntity enrollment) {
        EnrollEntity enrollEntity;
        Optional<EnrollEntity> searchEntity = enrollRepository.findById(id);
        if (searchEntity.isPresent()){
            EnrollEntity searchResult = searchEntity.get();
            searchResult.setYearTaken(enrollment.getYearTaken());
            searchResult.setScore(enrollment.getScore());
            enrollEntity = enrollRepository.save(searchResult);
        } else {
            throw new EntityNotFoundException();
        }
        return enrollEntity;
    }

    public ResponseEntity<Object> delete(Long Id) {
        Optional<EnrollEntity> searchEntity = enrollRepository.findById(Id);
        if (searchEntity.isPresent()) {
            EnrollEntity enrollEntity = searchEntity.get();
            enrollRepository.delete(enrollEntity);
        } else {
            throw new EntityNotFoundException();
        }
        return ResponseEntity.ok().build();
    }
}
