package my.ah.school_be.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "student")
public class StudentEntity extends Audit {
    @javax.persistence.Id
    @Column(name = "student_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @NotNull
    @Column(name = "first_name")
    public String firstName;

    @NotNull
    @Column(name = "last_name")
    public String lastName;

    @NotNull
    @Column(name = "mobile")
    public Long mobile;

    @NotNull
    @Column(name = "nric")
    public Long nric;

    @NotNull
    @Column(name = "gender")
    public int gender;

    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "student")
    @JsonBackReference
    private List<StudentCourseEntity> students;

    public StudentEntity() {
    }

    public StudentEntity(Long id, String firstName, String lastName, Long mobile, Long nric, int gender) {
        Id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.mobile = mobile;
        this.nric = nric;
        this.gender = gender;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Long getMobile() {
        return mobile;
    }

    public void setMobile(Long mobile) {
        this.mobile = mobile;
    }

    public Long getNric() {
        return nric;
    }

    public void setNric(Long nric) {
        this.nric = nric;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public List<StudentCourseEntity> getStudents() {
        return students;
    }

    public void setStudents(List<StudentCourseEntity> students) {
        this.students = students;
    }

    @Override
    public String toString() {
        return "StudentEntity{" +
                "Id=" + Id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", mobile=" + mobile +
                ", nric=" + nric +
                ", gender=" + gender +
                '}';
    }
}
