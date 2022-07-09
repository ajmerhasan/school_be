package my.ah.school_be.repository;

import my.ah.school_be.entity.StudentEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<StudentEntity, Long> {
    List<StudentEntity> findAll();
    //Page<StudentEntity> findAll(Pageable pageable);

//    List<StudentEntity> findByFirstNameOrLastNameContaining(String name);
    List<StudentEntity> findByFirstNameContaining(String name);
    List<StudentEntity> findByLastNameContaining(String name);

}
