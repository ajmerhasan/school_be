package my.ah.school_be.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "course")
public class CourseEntity extends Audit {
    @javax.persistence.Id
    @Column(name = "course_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @NotNull
    @Column(name = "course_name")
    public String courseName;

    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "course")
    @JsonBackReference
    private List<StudentCourseEntity> courses;

    public CourseEntity() {
    }

    public CourseEntity(Long id, String courseName) {
        Id = id;
        this.courseName = courseName;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public List<StudentCourseEntity> getCourses() {
        return courses;
    }

    public void setCourses(List<StudentCourseEntity> courses) {
        this.courses = courses;
    }

    @Override
    public String toString() {
        return "CourseEntity{" +
                "Id=" + Id +
                ", courseName='" + courseName + '\'' +
                '}';
    }
}
