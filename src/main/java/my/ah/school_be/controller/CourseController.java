package my.ah.school_be.controller;

import my.ah.school_be.entity.CourseEntity;
import my.ah.school_be.entity.StudentEntity;
import my.ah.school_be.service.CourseService;
import my.ah.school_be.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/v1/courses")
public class CourseController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CourseService courseService;

    @GetMapping
    public List<CourseEntity> getAll() {
        return courseService.findAll();
    }

    @GetMapping("/name/{name}")
    public List<CourseEntity> getCourseByCourseName(@PathVariable(value = "name") String name) {
        return courseService.findByCourseName(name);
    }

    @GetMapping("/{id}")
    public CourseEntity getCourseById(@PathVariable(value = "id") Long id) {
        return courseService.findById(id);
    }

    @PostMapping
    public CourseEntity create(@Valid @RequestBody CourseEntity course) {
        return courseService.save(course);
    }

    @PutMapping("/{id}")
    public CourseEntity update(@PathVariable("id") Long id, @Valid @RequestBody CourseEntity course) {
        return courseService.update(id, course);
    }

    @DeleteMapping("/{id}")
    public void remove(@PathVariable("id") Long id){
        courseService.delete(id);
    }
}
