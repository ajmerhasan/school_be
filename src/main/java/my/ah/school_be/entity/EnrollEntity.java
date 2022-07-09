package my.ah.school_be.entity;


import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
//@ToString
@Entity
//@DynamicUpdate
@Table(name = "enroll")
public class EnrollEntity extends Audit {
    @javax.persistence.Id
    @Column(name = "enroll_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    //@ManyToOne(fetch = FetchType.LAZY, optional = false)
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "student_id", nullable = false)
    //@JsonManagedReference
    //@JsonProperty("student")
    public StudentEntity student;

    //@ManyToOne(fetch = FetchType.LAZY, optional = false)
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "course_id", nullable = false)
    //@JsonManagedReference
    //@JsonProperty("course")
    public CourseEntity course;

    @NotNull
    @Column(name = "year_taken")
    public int yearTaken;

    @NotNull
    @Column(name = "score")
    public int score;

    public EnrollEntity setStudent(StudentEntity student) {
        this.student = student;
        return this;
    }

    public EnrollEntity setCourse(CourseEntity course) {
        this.course = course;
        return this;
    }
}
