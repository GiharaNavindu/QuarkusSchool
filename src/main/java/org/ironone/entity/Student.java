package org.ironone.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;
import lombok.*;


@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Student")
public class Student {
    @Id
    @Column(name = "Student_Id", length = 20)
    private String studentId;

    @Column(name = "First_Name", nullable = false)
    private String firstName;

    @Column(name = "Last_Name", nullable = false)
    private String lastName;

    @Column(name = "Email", nullable = false, unique = true)
    private String email;

    @Column(name = "Age")
    private Integer age;

    @Column(name = "DOB", nullable = false)
    private LocalDate dob;

    @Column(name = "Address")
    private String address;

    @Column(name = "Batch")
    private String batch;


//    @Column(name = "User_Id", nullable = false, unique=true)
//    private String userId;

    @JsonIgnore
    @OneToMany(mappedBy = "student", fetch = FetchType.EAGER)
    private List<Enrolls> enrollments;

    @ManyToMany
    @JoinTable(
            name = "Attendance",
            joinColumns = @JoinColumn(name = "Student_Id"),
            inverseJoinColumns = @JoinColumn(name = "Lecture_Id")
    )

    @JsonIgnore
    private List<Lecture> lectures;



}