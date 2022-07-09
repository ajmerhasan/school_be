package my.ah.school_be.service;

import my.ah.school_be.entity.StudentEntity;
import my.ah.school_be.repository.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    StudentRepository studentRepository;

    public List<StudentEntity> findAll(){
        logger.info("------- inside service -------");
        return studentRepository.findAll();
    }

    public List<StudentEntity> findByFirstName(String name) {
        //List<StudentEntity> searchResult = studentRepository.findByFirstNameOrLastNameContaining(name);
        List<StudentEntity> searchResult = studentRepository.findByFirstNameContaining(name);
        return searchResult;
    }

    public List<StudentEntity> findByLastName(String name) {
        List<StudentEntity> searchResult = studentRepository.findByLastNameContaining(name);
        return searchResult;
    }

    public StudentEntity findById(Long Id) {
        StudentEntity searchId;
        Optional<StudentEntity> searchEntity = studentRepository.findById(Id);

        if (searchEntity.isPresent()){
            searchId = searchEntity.get();
        } else {
            throw new EntityNotFoundException();
        }
        return searchId;
    }

    public StudentEntity save(StudentEntity newStudent) {
        return studentRepository.save(newStudent);
    }

    public StudentEntity update(Long Id, StudentEntity studentEntity) {
        StudentEntity updatedStudentEntity;
        Optional<StudentEntity> searchEntity = studentRepository.findById(Id);

        if (searchEntity.isPresent()){
            StudentEntity student = searchEntity.get();
            student.setFirstName(studentEntity.getFirstName());
            student.setLastName(studentEntity.getLastName());
            student.setMobile(studentEntity.getMobile());
            student.setNric(studentEntity.getNric());
            student.setGender(studentEntity.getGender());
            student.setUpdatedBy(studentEntity.getUpdatedBy());

            updatedStudentEntity = studentRepository.save(student);
        } else {
            throw new EntityNotFoundException();
        }
        return updatedStudentEntity;
    }

    public ResponseEntity<Object> delete(Long Id) {
        Optional<StudentEntity> searchEntity = studentRepository.findById(Id);
        if (searchEntity.isPresent()) {
            StudentEntity bisonMessage = searchEntity.get();
            studentRepository.delete(bisonMessage);
        } else {
            throw new EntityNotFoundException();
        }
        return ResponseEntity.ok().build();
    }
}
