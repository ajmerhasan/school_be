package my.ah.school_be.repository;

import my.ah.school_be.entity.CourseEntity;
import my.ah.school_be.entity.StudentCourseEntity;
import my.ah.school_be.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentCourseRepository extends JpaRepository<StudentCourseEntity, Long> {
    List<StudentCourseEntity> findAll();
    //Page<StudentCourseEntity> findAll(Pageable pageable);

    List<StudentCourseEntity> findByStudent(StudentEntity studentEntity);
    List<StudentCourseEntity> findByCourse(CourseEntity courseEntity);
}
