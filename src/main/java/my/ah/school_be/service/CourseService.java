package my.ah.school_be.service;

import my.ah.school_be.entity.CourseEntity;
import my.ah.school_be.repository.CourseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CourseService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    CourseRepository courseRepository;

    public CourseEntity save(CourseEntity newCourse) {
        Optional<CourseEntity> findCourse = Optional.ofNullable(courseRepository.findByCourseName(newCourse.getCourseName()));

        if (findCourse.isPresent()) {
            logger.info("{}", "Course name "+ newCourse.getCourseName() + " is already exists");
            return findCourse.get();
        } else {
            logger.info("{}", "Course name "+ newCourse.getCourseName() + " not found, creating new one...");
            return courseRepository.save(newCourse);
        }
    }
}
