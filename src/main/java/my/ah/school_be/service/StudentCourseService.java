package my.ah.school_be.service;

import my.ah.school_be.entity.CourseEntity;
import my.ah.school_be.entity.StudentCourseEntity;
import my.ah.school_be.entity.StudentEntity;
import my.ah.school_be.repository.CourseRepository;
import my.ah.school_be.repository.StudentCourseRepository;
import my.ah.school_be.repository.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Service
public class StudentCourseService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    private Environment env;

    @Autowired
    StudentCourseRepository studentCourseRepository;

    public List<StudentCourseEntity> findAll() {
        return studentCourseRepository.findAll();
    }


    public List<StudentCourseEntity> findByStudent(StudentEntity student) {
        List<StudentCourseEntity> searchEntity = (List<StudentCourseEntity>) studentCourseRepository.findByStudent(student);
        return searchEntity;
    }

    public List<StudentCourseEntity> findByCourse(CourseEntity course) {
        List<StudentCourseEntity> searchEntity = (List<StudentCourseEntity>) studentCourseRepository.findByCourse(course);
        return searchEntity;
    }

    public StudentCourseEntity save(Long studentId, Long courseId, StudentCourseEntity enrollment) {
        System.out.println("\n" + enrollment.getCourse() + "\n");

        Optional<StudentEntity> schStudent = studentRepository.findById(studentId);
        if (!schStudent.isPresent()){
            throw new EntityNotFoundException("student id " + studentId + " does not exist in record");
        }

        Optional<CourseEntity> schCourse = courseRepository.findById(courseId);
        if (!schCourse.isPresent()){
            throw new EntityNotFoundException("course id " + schCourse + " does not exist in record");
        }

        StudentCourseEntity enroll = new StudentCourseEntity();
        studentRepository.findById(studentId).map((Function<StudentEntity, Object>) enroll::setStudent);
        courseRepository.findById(courseId).map((Function<CourseEntity, Object>) enroll::setCourse);
        enroll.setYearTaken(enrollment.getYearTaken());
        enroll.setScore(enrollment.getScore());

        return studentCourseRepository.save(enroll);
    }

    public StudentCourseEntity updateYear(StudentEntity studentId, CourseEntity courseId, StudentCourseEntity enrollment) {
        StudentCourseEntity studentCourseEntity;
        Optional<StudentCourseEntity> searchEntity = studentCourseRepository.findByStudentAndCourse(studentId,courseId);
        if (searchEntity.isPresent()){
            StudentCourseEntity searchResult = searchEntity.get();
            searchResult.setYearTaken(enrollment.getYearTaken());
            studentCourseEntity = studentCourseRepository.save(searchResult);
        } else {
            throw new EntityNotFoundException();
        }
        return studentCourseEntity;
    }

    public StudentCourseEntity updateScore(StudentEntity studentId, CourseEntity courseId, StudentCourseEntity enrollment) {
        StudentCourseEntity studentCourseEntity;
        Optional<StudentCourseEntity> searchEntity = studentCourseRepository.findByStudentAndCourse(studentId,courseId);
        if (searchEntity.isPresent()){
            StudentCourseEntity searchResult = searchEntity.get();
            searchResult.setScore(enrollment.getScore());
            studentCourseEntity = studentCourseRepository.save(searchResult);
        } else {
            throw new EntityNotFoundException();
        }
        return studentCourseEntity;
    }

    public ResponseEntity<Object> delete(Long Id) {
        Optional<StudentCourseEntity> searchEntity = studentCourseRepository.findById(Id);
        if (searchEntity.isPresent()) {
            StudentCourseEntity studentCourseEntity = searchEntity.get();
            studentCourseRepository.delete(studentCourseEntity);
        } else {
            throw new EntityNotFoundException();
        }
        return ResponseEntity.ok().build();
    }
}
