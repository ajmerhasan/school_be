package my.ah.school_be.repository;

import my.ah.school_be.entity.CourseEntity;
import my.ah.school_be.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<CourseEntity, Long> {
    CourseEntity findByCourseName(String courseName);
    List<CourseEntity> findByCourseNameContaining(String courseName);

}
