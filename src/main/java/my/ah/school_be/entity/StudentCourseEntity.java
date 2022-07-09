package my.ah.school_be.entity;


import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "student_course")
public class StudentCourseEntity extends Audit {
    @javax.persistence.Id
    @Column(name = "student_course_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "student_id", nullable = false)
    //@JsonManagedReference
    public StudentEntity student;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "course_id", nullable = false)
    //@JsonManagedReference
    public CourseEntity course;

    @NotNull
    @Column(name = "year_taken")
    public int yearTaken;

    @NotNull
    @Column(name = "score")
    public int score;


}
