package my.ah.school_be.controller;

import my.ah.school_be.entity.CourseEntity;
import my.ah.school_be.entity.EnrollEntity;
import my.ah.school_be.entity.StudentEntity;
import my.ah.school_be.service.EnrollService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/v1/enrollment")
public class EnrollController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private EnrollService enrollService;

    @GetMapping
    public List<EnrollEntity> getAll() {
        return enrollService.findAll();
    }

    @GetMapping("/student/{id}")
    public List<EnrollEntity> getEnrollmentByStudentId(@PathVariable(value = "id") StudentEntity id){
        logger.info("Values:{}", " >>> " + id + " <<< ");
        return enrollService.findByStudent(id);
    }

    @GetMapping("/course/{id}")
    public List<EnrollEntity> getEnrollmentByCourseId(@PathVariable(value = "id") CourseEntity id){
        logger.info("Values:{}", " >>> " + id + " <<< ");
        return enrollService.findByCourse(id);
    }

    @PostMapping("/student/{studentId}/course/{courseId}")
    public EnrollEntity create(@Valid @PathVariable("studentId") Long studentId,
                               @Valid @PathVariable("courseId") Long courseId,
                               @Valid @RequestBody EnrollEntity enrollment) {
        //student.setCreatedBy("demo");
        return enrollService.save(studentId, courseId, enrollment);
    }

    @PutMapping("/year/student/{studentId}/course/{courseId}")
    public EnrollEntity updateYear(@Valid @PathVariable("studentId") StudentEntity studentId,
                                   @Valid @PathVariable("courseId") CourseEntity courseId,
                                   @Valid @RequestBody EnrollEntity enrollment) {
        return enrollService.updateYear(studentId, courseId, enrollment);
    }

    @PutMapping("/score/student/{studentId}/course/{courseId}")
    public EnrollEntity updateScore(@Valid @PathVariable("studentId") StudentEntity studentId,
                                    @Valid @PathVariable("courseId") CourseEntity courseId,
                                    @Valid @RequestBody EnrollEntity enrollment) {
        return enrollService.updateScore(studentId, courseId, enrollment);
    }

    @DeleteMapping("/{id}")
    public void remove(@PathVariable("id") Long id){
        enrollService.delete(id);
    }
}
