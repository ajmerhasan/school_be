package my.ah.school_be.service;

import my.ah.school_be.repository.StudentCourseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentCourseService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    StudentCourseRepository studentCourseRepository;
}
