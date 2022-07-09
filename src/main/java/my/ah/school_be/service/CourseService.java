package my.ah.school_be.service;

import my.ah.school_be.entity.CourseEntity;
import my.ah.school_be.entity.StudentEntity;
import my.ah.school_be.repository.CourseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class CourseService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    CourseRepository courseRepository;

    public List<CourseEntity> findAll(){
        return courseRepository.findAll();
    }

    public List<CourseEntity> findByCourseName(String name) {
        List<CourseEntity> searchResult = courseRepository.findByCourseNameContaining(name);
        return searchResult;
    }

    public CourseEntity findById(Long Id) {
        CourseEntity searchId;
        Optional<CourseEntity> searchEntity = courseRepository.findById(Id);

        if (searchEntity.isPresent()){
            searchId = searchEntity.get();
        } else {
            throw new EntityNotFoundException();
        }
        return searchId;
    }

    public CourseEntity save(CourseEntity newCourse) {
        Optional<CourseEntity> findCourse = Optional.ofNullable(courseRepository.findByCourseName(newCourse.getCourseName()));

        if (findCourse.isPresent()) {
            logger.info("{}", "Course name "+ newCourse.getCourseName() + " is already exists");
            return findCourse.get();
        } else {
            return courseRepository.save(newCourse);
        }
    }

    public CourseEntity update(Long Id, CourseEntity courseEntity) {
        CourseEntity updatedCourseEntity;
        Optional<CourseEntity> searchEntity = courseRepository.findById(Id);

        if (searchEntity.isPresent()){
            CourseEntity course = searchEntity.get();
            course.setCourseName(courseEntity.getCourseName());
            course.setUpdatedBy(courseEntity.getUpdatedBy());

            updatedCourseEntity = courseRepository.save(course);
        } else {
            throw new EntityNotFoundException();
        }
        return updatedCourseEntity;
    }

    public ResponseEntity<Object> delete(Long Id) {
        Optional<CourseEntity> searchEntity = courseRepository.findById(Id);
        if (searchEntity.isPresent()) {
            CourseEntity courseEntity = searchEntity.get();
            courseRepository.delete(courseEntity);
        } else {
            throw new EntityNotFoundException();
        }
        return ResponseEntity.ok().build();
    }
}
