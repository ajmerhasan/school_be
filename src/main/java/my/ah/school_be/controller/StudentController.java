package my.ah.school_be.controller;

import my.ah.school_be.entity.StudentEntity;
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
@RequestMapping("/v1/students")
public class StudentController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private StudentService studentService;

    @GetMapping
    public List<StudentEntity> getAll() {
        //logger.info(studentService.findAll().toString());
        return studentService.findAll();
    }

    @GetMapping("/first-name/{name}")
    public List<StudentEntity> getStudentByFirstName(@PathVariable(value = "name") String name) {
        return studentService.findByFirstName(name);
    }

    @GetMapping("/last-name/{name}")
    public List<StudentEntity> getStudentByLastName(@PathVariable(value = "name") String name) {
        return studentService.findByLastName(name);
    }

    @GetMapping("/{id}")
    public StudentEntity getStudentById(@PathVariable(value = "id") Long id) {
        return studentService.findById(id);
    }

    @PostMapping
    public StudentEntity create(@Valid @RequestBody StudentEntity student) {
        //student.setCreatedBy("demo");
        return studentService.save(student);
    }

    @PutMapping("/{id}")
    public StudentEntity update(@PathVariable("id") Long id, @Valid @RequestBody StudentEntity student) {
        return studentService.update(id, student);
    }

    @DeleteMapping("/{id}")
    public void remove(@PathVariable("id") Long id){
        studentService.delete(id);
    }
}
