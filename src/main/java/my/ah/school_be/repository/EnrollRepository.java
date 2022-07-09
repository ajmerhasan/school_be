package my.ah.school_be.repository;

import my.ah.school_be.entity.CourseEntity;
import my.ah.school_be.entity.EnrollEntity;
import my.ah.school_be.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EnrollRepository extends JpaRepository<EnrollEntity, Long> {
    List<EnrollEntity> findAll();
    //Page<StudentCourseEntity> findAll(Pageable pageable);

    List<EnrollEntity> findByStudent(StudentEntity studentId);
    List<EnrollEntity> findByCourse(CourseEntity courseId);

    Optional<EnrollEntity> findByStudentAndCourse(StudentEntity studentId, CourseEntity courseId);
}
