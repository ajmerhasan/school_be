package my.ah.school_be.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
//@ToString
@Entity
@DynamicUpdate
@Table(name = "student")
//@org.hibernate.annotations.Entity(
//        dynamicUpdate = true
//)
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
    private List<EnrollEntity> students;
}
