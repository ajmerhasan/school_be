package my.ah.school_be.controller;

import my.ah.school_be.entity.CourseEntity;
import my.ah.school_be.entity.StudentCourseEntity;
import my.ah.school_be.entity.StudentEntity;
import my.ah.school_be.service.CourseService;
import my.ah.school_be.service.StudentCourseService;
import my.ah.school_be.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.MalformedURLException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;

@RestController
@RequestMapping("/v1/enrollment")
public class StudentCourseController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private StudentCourseService studentCourseService;

    @GetMapping
    public List<StudentCourseEntity> getAll() {
        return studentCourseService.findAll();
    }

    @GetMapping("/student/{id}")
    public List<StudentCourseEntity> getEnrollmentByStudentId(@PathVariable(value = "id") StudentEntity id){
        logger.info("Values:{}", " >>> " + id + " <<< ");
        return studentCourseService.findByStudent(id);
    }

    @GetMapping("/course/{id}")
    public List<StudentCourseEntity> getEnrollmentByCourseId(@PathVariable(value = "id") CourseEntity id){
        logger.info("Values:{}", " >>> " + id + " <<< ");
        return studentCourseService.findByCourse(id);
    }

    @PostMapping("/student/{studentId}/course/{courseId}")
    public StudentCourseEntity create(@Valid @PathVariable("studentId") Long studentId,
                                      @Valid @PathVariable("courseId") Long courseId,
                                      @Valid @RequestBody StudentCourseEntity enrollment) {
        //student.setCreatedBy("demo");
        return studentCourseService.save(studentId, courseId, enrollment);
    }

    @PutMapping("/year/student/{studentId}/course/{courseId}")
    public StudentCourseEntity updateYear(@Valid @PathVariable("studentId") StudentEntity studentId,
                                      @Valid @PathVariable("courseId") CourseEntity courseId,
                                      @Valid @RequestBody StudentCourseEntity enrollment) {
        return studentCourseService.updateYear(studentId, courseId, enrollment);
    }

    @PutMapping("/score/student/{studentId}/course/{courseId}")
    public StudentCourseEntity updateScore(@Valid @PathVariable("studentId") StudentEntity studentId,
                                          @Valid @PathVariable("courseId") CourseEntity courseId,
                                          @Valid @RequestBody StudentCourseEntity enrollment) {
        return studentCourseService.updateScore(studentId, courseId, enrollment);
    }

    @DeleteMapping("/{id}")
    public void remove(@PathVariable("id") Long id){
        studentCourseService.delete(id);
    }
}
